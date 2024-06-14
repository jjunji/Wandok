#!/bin/sh

echo "Running detekt tests"

# 변경된 파일 목록 가져오기
FILES=$(git diff --cached --name-only --diff-filter=ACM | grep '\.kt$')

if [ -n "$FILES" ]; then
    echo "Detected changes in the following files:"
    echo "$FILES"
    echo "Number of detected files: $(echo "$FILES" | wc -l)"

    # 변경된 파일에 대해서만 Detekt 실행
    ./gradlew detekt --filters FILES
    DETEKT_STATUS=$?

    if [ "$DETEKT_STATUS" -eq 0 ]; then
        echo "Detekt tests passed"
    else
        echo "Detekt tests failed"
        exit 1
    fi
else
    echo "No changes detected"
fi

exit 0


# ./scripts/pre-commit-test.sh