import os
import re
import sys
import subprocess
from pathlib import Path

EXCLUDED_DIRS = {"out", ".idea", ".git", "__pycache__"}


def clear_screen():
    """Clear the terminal screen for readability."""
    os.system("cls" if os.name == "nt" else "clear")


# ---------- Helpers ----------
def open_in_explorer(path: Path):
    """Open the folder in the system's file explorer."""
    try:
        if os.name == "nt":  # Windows
            os.startfile(path)
        elif sys.platform == "darwin":  # macOS
            subprocess.run(["open", path])
        else:  # Linux and others
            subprocess.run(["xdg-open", path])
    except Exception as e:
        print(f"Could not open {path}: {e}")
        input("\nPress Enter to continue...")


def read_notes(file_path: Path):
    """Reads and returns the content of a Notes.md file safely."""
    try:
        with open(file_path, "r", encoding="utf-8") as f:
            return f.read()
    except Exception as e:
        print(f"Could not read {file_path}: {e}")
        return None


def find_notes_files(base_dir: Path):
    """Yields all Notes.md files in the project (excluding unwanted folders)."""
    for notes_file in base_dir.rglob("Notes.md"):
        if any(part in EXCLUDED_DIRS for part in notes_file.parts):
            continue
        yield notes_file


def extract_problem_number(path: Path):
    """
    Extract the numeric part from a folder name like s0004_MedianOfTwoSortedArrays.
    Returns an integer for proper sorting.
    """
    match = re.search(r"s(\d{4})_", path.parent.name)
    if match:
        return int(match.group(1))
    return float("inf")  # put non-matching ones at the end


# ---------- Features ----------
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
        if match:
            topics_line = match.group(1).lower()
            if topic in topics_line:
                title_match = re.search(r"# Problem:\s*(.*)", content)
                title = title_match.group(1).strip() if title_match else "Unknown Problem"
                matches.append((title, notes_file))

    if not matches:
        print(f"No problems found with topic: {topic}")
        input("\nPress Enter to return to menu...")
        clear_screen()
        return

    # Sort results by problem number
    matches.sort(key=lambda x: extract_problem_number(x[1]))

    print(f"Results for topic: {topic}\n")
    for i, (title, path) in enumerate(matches, start=1):
        rel_path = path.relative_to(base_dir)
        print(f"[{i}] {title}")
        print(f"    {rel_path}\n")

    choice = input("Enter the number of a problem to open its folder (or press Enter to skip): ").strip()
    if choice.isdigit():
        idx = int(choice) - 1
        if 0 <= idx < len(matches):
            problem_dir = matches[idx][1].parent
            print(f"Opening folder: {problem_dir}")
            open_in_explorer(problem_dir)
            input("\nPress Enter to return to menu...")
        else:
            print("Invalid selection.")
            input("\nPress Enter to return to menu...")
    clear_screen()


def list_all_problems(base_dir: Path):
    clear_screen()
    problems = []
    for notes_file in find_notes_files(base_dir):
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

    # Sort by problem number
    problems.sort(key=lambda x: extract_problem_number(x[1]))

    print("All Solved Problems:\n")
    for i, (title, path) in enumerate(problems, start=1):
        rel_path = path.relative_to(base_dir)
        print(f"[{i}] {title}")
        print(f"    {rel_path}\n")

    print(f"Total Problems: {len(problems)}")
    choice = input("\nEnter the number of a problem to open its folder (or press Enter to skip): ").strip()
    if choice.isdigit():
        idx = int(choice) - 1
        if 0 <= idx < len(problems):
            problem_dir = problems[idx][1].parent
            print(f"Opening folder: {problem_dir}")
            open_in_explorer(problem_dir)
            input("\nPress Enter to return to menu...")
        else:
            print("Invalid selection.")
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
        print("3. Exit")

        choice = input("\nEnter choice (1-3): ").strip()

        if choice == "1":
            search_by_topic(base_dir)
        elif choice == "2":
            list_all_problems(base_dir)
        elif choice == "3":
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
