#!/usr/bin/env bash
set -euo pipefail

repo_root="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
project_dir="$repo_root/FootballPlayers (1)"
build_file="$project_dir/build.xml"

if [[ ! -f "$build_file" ]]; then
  echo "Build file not found: $build_file" >&2
  exit 1
fi

find_ant() {
  local candidates=(
    "${NETBEANS_HOME:-}/ant/bin/ant"
    "${HOME}/netbeans-13/ant/bin/ant"
    "/opt/netbeans-13/ant/bin/ant"
    "/usr/local/netbeans-13/ant/bin/ant"
    "/usr/share/netbeans-13/ant/bin/ant"
    "/usr/bin/ant"
  )

  for candidate in "${candidates[@]}"; do
    if [[ -x "$candidate" ]]; then
      echo "$candidate"
      return 0
    fi
  done

  return 1
}

ant_bin="$(find_ant || true)"
if [[ -z "${ant_bin:-}" ]]; then
  echo "Ant executable not found. Attempting to install system Ant..."
  if command -v apt-get >/dev/null 2>&1; then
    sudo apt-get update
    sudo apt-get install -y ant
    ant_bin="$(command -v ant)"
  else
    echo "Unable to install Ant automatically." >&2
    exit 1
  fi
fi

echo "Using Ant executable: $ant_bin"
"$ant_bin" -f "$build_file" "$@"
