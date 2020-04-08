//`Array#map`を使って配列の全ての要素を2倍した配列を作る処理を実装せよ
console.log([1, 2, 3]);
// [2, 4, 6]

//`for`文を使って`Array#map`を実装せよ
function map() {}

console.log(map([1, 2, 3], (e) => e * 2));
// [2, 4, 6]

console.log(map(["a", "b", "c"], (e) => "(" + e + ")"));
// ["(a)", "(b)", "(c)"]

export function test() {
  const arr = [1, 2, 3];

  console.log(arr.map((e) => e * 2));
  console.log(arr.map((e, i) => e * i));

  console.log(map(arr, (e) => e * 2));
  console.log(map(arr, (e, i) => e * i));
}
