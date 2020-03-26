# juku

```sh
# `skip-worktree`で`sandbox.js`をgitの差分の追跡から除外する
git update-index --skip-worktree src/sandbox.ts
# 元に戻す
git update-index --no-skip-worktree src/sandbox.ts

# `skip-worktree`されているファイルの一覧を確認
git ls-files -v | grep '^s'
```
