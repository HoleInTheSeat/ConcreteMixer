#!/bin/sh
set -eu
cd "$(dirname "$0")"
mvn --batch-mode clean verify
