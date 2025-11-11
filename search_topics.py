import os
import re
import sys
from pathlib import Path

EXCLUDED_DIRS = {"out", ".idea", ".git", "__pycache__"}

def search_topics(base_dir: Path, topic: str):
    topic = topic.lower()
    matches = []

    # all subdirectories
    for notes_file in base_dir.rglob("Notes.md"):
        if any(part in EXCLUDED_DIRS for part in notes_file.parts):
            continue

        try:
            with open(notes_file, "r", encoding="utf-8") as f:
                content = f.read()
        except Exception as e:
            print(f"Could not read {notes_file}: {e}")
            continue

        # Look for specific pattern
        match = re.search(r"## Topics:\s*(.*)", content, re.IGNORECASE)
        if match:
            topics_line = match.group(1).lower()
            if topic in topics_line:
                title_match = re.search(r"# Problem:\s*(.*)", content)
                title = title_match.group(1).strip() if title_match else "Unknown Problem"
                matches.append((title, notes_file))

    return matches


def main():
    if len(sys.argv) < 2:
        print("Usage: python search_topics.py <topic>")
        print("Note: Singular words only!!")
        print("Example: python search_topics.py Array")
        sys.exit(1)

    topic = sys.argv[1]
    base_dir = Path(os.getcwd())  # current folder (project root)

    print(f"\nSearching for topic: {topic}\n")

    results = search_topics(base_dir, topic)

    if results:
        for title, path in results:
            rel_path = path.relative_to(base_dir)
            print(f"✅ {title}")
            print(f"   ↳ {rel_path}\n")
    else:
        print(f"No problems found with topic: {topic}")
if __name__ == "__main__":
    main()