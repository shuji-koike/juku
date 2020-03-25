//`Array#map`を使って配列の全ての要素を2倍した配列を作る処理を実装せよ
console.log([1, 2, 3]);
// > [2, 4, 6]

//`for`文を使って`Array#map`を実装せよ
function map() {}

export function test() {
  const arr = [1, 2, 3];

  console.log(arr.map(e => e * 2));
  console.log(arr.map((e, i) => e * i));

  console.log(map(arr, e => e * 2));
  console.log(map(arr, (e, i) => e * i));
}
