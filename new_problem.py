import os
import sys
import re
from pathlib import Path

# --- Configuration ---
SRC_DIR = Path("src")  # your source folder

# --- Helpers ---
def to_folder_name(problem_number: int, title: str, category: str):
    """Create a folder path like src/string/s0003_LongestSubstringWithoutRepeatingCharacters"""
    slug = re.sub(r'[^A-Za-z0-9]', '', title.title().replace(' ', ''))
    # Create nested path: src/category/sXXXX_Title
    return SRC_DIR / category / f"s{problem_number:04d}_{slug}"

def to_class_name(title: str):
    """Convert a problem title into a valid Java class name"""
    return re.sub(r'[^A-Za-z0-9]', '', title.title().replace(' ', ''))

# --- Main ---
def main():
    if len(sys.argv) < 4:
        print("Usage: python new_problem.py <number> <title> <category> [topics]")
        print("Example: python new_problem.py 3 'Longest Substring Without Repeating Characters' string 'HashTable, SlidingWindow'")
        sys.exit(1)

    problem_number = int(sys.argv[1])
    title = sys.argv[2]
    category = sys.argv[3]
    topics = sys.argv[4] if len(sys.argv) > 4 else ""

    folder_path = to_folder_name(problem_number, title, category)
    class_name = to_class_name(title)
    link = f"https://leetcode.com/problems/{'-'.join(title.lower().split())}/"

    # Create directories (category + problem folder)
    os.makedirs(folder_path, exist_ok=True)

    # Create Java file
    java_file = folder_path / f"{class_name}.java"
    java_template = f"""package {category}.s{problem_number:04d}_{class_name};

public class {class_name} {{

    public void solve() {{
        // TODO: Implement solution
    }}

    public static void main(String[] args) {{
        System.out.println("Running {class_name}...");
    }}
}}
"""
    with open(java_file, "w", encoding="utf-8") as f:
        f.write(java_template)

    # Create Notes.md
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

    print(f"✅ Created: {folder_path}")
    print(f"├── {class_name}.java")
    print(f"└── Notes.md")


if __name__ == "__main__":
    main()
