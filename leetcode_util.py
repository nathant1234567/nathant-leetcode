import os
import re
import sys
import subprocess
from pathlib import Path
from collections import defaultdict

EXCLUDED_DIRS = {"out", ".idea", ".git", "__pycache__"}
SRC_DIR = Path("src")  # Base folder for new problems


def clear_screen():
    os.system("cls" if os.name == "nt" else "clear")


# ---------- Helpers ----------
def open_in_explorer(path: Path):
    try:
        if os.name == "nt":
            os.startfile(path)  # type: ignore[attr-defined]
        elif sys.platform == "darwin":
            subprocess.run(["open", str(path)], check=False)
        else:
            subprocess.run(["xdg-open", str(path)], check=False)
    except Exception as e:
        print(f"Could not open {path}: {e}")
        input("\nPress Enter to continue...")


def read_notes(file_path: Path):
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            return f.read()
    except Exception as e:
        print(f"Could not read {file_path}: {e}")
        return None


def find_notes_files(base_dir: Path):
    for notes_file in base_dir.rglob("Notes.md"):
        if any(part in EXCLUDED_DIRS for part in notes_file.parts):
            continue
        yield notes_file


def extract_problem_number(notes_md_path: Path) -> int:
    parent = notes_md_path.parent.name
    m = re.match(r"s(\d{4})_", parent)
    if m:
        return int(m.group(1))
    return 10**9


def get_category(notes_md_path: Path) -> str:
    try:
        category = notes_md_path.parent.parent.name
        return category.lower()
    except Exception:
        return "unknown"


# ---------- New Problem Helpers ----------
def to_folder_name(problem_number: int, title: str, category: str):
    slug = re.sub(r"[^A-Za-z0-9]", "", title.title().replace(" ", ""))
    return SRC_DIR / category / f"s{problem_number:04d}_{slug}"


def to_class_name(title: str):
    return re.sub(r"[^A-Za-z0-9]", "", title.title().replace(" ", ""))


def create_new_problem():
    clear_screen()
    print("=== Create New Problem ===\n")
    try:
        problem_number = int(input("Enter problem number (e.g., 7): ").strip())
    except ValueError:
        print("Invalid number. Returning to menu.")
        input("\nPress Enter to continue...")
        clear_screen()
        return

    title = input("Enter problem title (e.g., Reverse Integer): ").strip()
    if not title:
        print("Title cannot be empty.")
        input("\nPress Enter to continue...")
        clear_screen()
        return

    category = input("Enter category (e.g., math, strings, arrays): ").strip().lower()
    if not category:
        print("Category cannot be empty.")
        input("\nPress Enter to continue...")
        clear_screen()
        return

    topics = input("Enter topics (comma-separated, optional): ").strip()

    folder_path = to_folder_name(problem_number, title, category)
    class_name = to_class_name(title)
    link = f"https://leetcode.com/problems/{'-'.join(title.lower().split())}/"

    os.makedirs(folder_path, exist_ok=True)

    java_file = folder_path / f"{class_name}.java"
    java_template = f"""package {category}.s{problem_number:04d}_{class_name};

public class {class_name} {{

    public void solve() {{
        // TODO: Implement solution
    }}

    public static void main(String[] args) {{
        {class_name} solution = new {class_name}();
        System.out.println(solution.functionhere());
    }}
}}
"""
    with open(java_file, "w", encoding="utf-8") as f:
        f.write(java_template)

    notes_file = folder_path / "Notes.md"
    notes_template = f"""# Problem: {title}
## Link: {link}
## Topics: {topics}

### Approach:
-

### Time Complexity:
-

### Space Complexity:
-
"""
    with open(notes_file, "w", encoding="utf-8") as f:
        f.write(notes_template)

    clear_screen()
    print("Problem created successfully!\n")
    print(f"Folder: {folder_path}")
    print(f"├── {class_name}.java")
    print(f"└── Notes.md\n")

    input("Press Enter to open the folder...")
    open_in_explorer(folder_path)
    input("\nPress Enter to return to menu...")
    clear_screen()


# ---------- Existing Features ----------
def search_by_topic(base_dir: Path):
    clear_screen()
    topic = input("Enter topic to search (e.g. Arrays, String, DP): ").strip().lower()
    clear_screen()
    if not topic:
        print("No topic entered. Returning to menu.")
        input("\nPress Enter to continue...")
        return

    matches = []
    for notes_file in find_notes_files(base_dir):
        content = read_notes(notes_file)
        if not content:
            continue
        match = re.search(r"## Topics:\s*(.*)", content, re.IGNORECASE)
        if match and topic in match.group(1).lower():
            title_match = re.search(r"# Problem:\s*(.*)", content)
            title = title_match.group(1).strip() if title_match else "Unknown Problem"
            matches.append((title, notes_file))

    if not matches:
        print(f"No problems found with topic: {topic}")
        input("\nPress Enter to return to menu...")
        clear_screen()
        return

    matches.sort(key=lambda x: extract_problem_number(x[1]))
    print(f"Results for topic: {topic}\n")
    for i, (title, path) in enumerate(matches, start=1):
        rel_path = path.relative_to(base_dir)
        print(f"[{i}] {title}")
        print(f"    {rel_path}\n")

    choice = input("Enter the number to open (or press Enter to skip): ").strip()
    if choice.isdigit():
        idx = int(choice) - 1
        if 0 <= idx < len(matches):
            open_in_explorer(matches[idx][1].parent)
            input("\nPress Enter to return to menu...")
    clear_screen()


def list_all_problems(base_dir: Path):
    clear_screen()
    category_filter = input("Enter category filter (or press Enter to show all): ").strip().lower()
    clear_screen()
    problems = []

    for notes_file in find_notes_files(base_dir):
        if category_filter and category_filter not in str(notes_file.parent).lower():
            continue
        content = read_notes(notes_file)
        if not content:
            continue
        title_match = re.search(r"# Problem:\s*(.*)", content)
        title = title_match.group(1).strip() if title_match else "Unknown Problem"
        problems.append((title, notes_file))

    if not problems:
        print("No problems found.")
        input("\nPress Enter to return to menu...")
        clear_screen()
        return

    problems.sort(key=lambda x: extract_problem_number(x[1]))
    print("All Solved Problems:\n")
    for i, (title, path) in enumerate(problems, start=1):
        rel_path = path.relative_to(base_dir)
        category = get_category(path)
        print(f"[{i}] {title}  ({category})")
        print(f"    {rel_path}\n")

    total = len(problems)
    print(f"Total Problems Solved: {total}")

    choice = input("\nEnter number to open (or press Enter to skip): ").strip()
    if choice.isdigit():
        idx = int(choice) - 1
        if 0 <= idx < len(problems):
            open_in_explorer(problems[idx][1].parent)
            input("\nPress Enter to return to menu...")
    clear_screen()


def show_category_counts(base_dir: Path):
    clear_screen()
    counts = defaultdict(int)
    for notes_file in find_notes_files(base_dir):
        counts[get_category(notes_file)] += 1

    if not counts:
        print("No problems found.")
        input("\nPress Enter to return to menu...")
        clear_screen()
        return

    print("Problems by Category:\n")
    for cat, count in sorted(counts.items()):
        print(f"{cat}: {count}")
    print(f"\nTotal Problems Solved: {sum(counts.values())}")
    input("\nPress Enter to return to menu...")
    clear_screen()


# ---------- Menu ----------
def main():
    base_dir = Path(os.getcwd())
    while True:
        clear_screen()
        print("=== LeetCode Utility Menu ===")
        print("1. Search by Topic")
        print("2. List All Problems")
        print("3. Show Category Counts")
        print("4. Add New Problem")
        print("5. Exit")

        choice = input("\nEnter choice (1-5): ").strip()
        if choice == "1":
            search_by_topic(base_dir)
        elif choice == "2":
            list_all_problems(base_dir)
        elif choice == "3":
            show_category_counts(base_dir)
        elif choice == "4":
            create_new_problem()
        elif choice == "5":
            clear_screen()
            print("Exiting. Goodbye!\n")
            break
        else:
            clear_screen()
            print("Invalid choice. Please try again.")
            input("\nPress Enter to continue...")
            clear_screen()


if __name__ == "__main__":
    main()
