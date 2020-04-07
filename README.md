# juku

- wiki (work in progress...)
  - [javascript](https://github.com/shuji-koike/juku/wiki/javascript)
  - [scala](https://github.com/shuji-koike/juku/wiki/scala)

# setup

```sh
# npmコマンドで依存パッケージをインストールする。`i`は`install`の略である
npm i

# `package.json`の`scritps.start`に指定された、
# `react-scripts start`コマンドが実装されて、httpサーバが起動する。
npm start
```

# memo

```sh
# `skip-worktree`で`sandbox.js`をgitの差分の追跡から除外する
git update-index --skip-worktree src/sandbox.ts
# 元に戻す
git update-index --no-skip-worktree src/sandbox.ts

# `skip-worktree`されているファイルの一覧を確認
git ls-files -v | grep '^s'
```

### reference

- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference
- https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array

- https://github.com/shuji-koike/juku/wiki/javascript
- https://github.com/shuji-koike/juku/wiki/scala
