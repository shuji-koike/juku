const arr = [1, 2, 3];
const obj = { a: 1, b: 2, c: 3 };

// `for`文を使ってarrayのコピーを返すfunctionを実装せよ
function copyArray(arr) {
  return arr;
}

// arr.slice()
// Array.from(arr)
// new Array(...arr)
// [...arr]
// [].push(...arr)

function test__copyArray() {
  assert(arr !== copyArray(arr));
  assert(arr[0] === copyArray(arr)[0]);
}

// `for in`文を使ってobjectのコピーを返すfunctionを実装せよ
function copyObject(obj) {
  return obj;
}

function test__copyObject() {
  assert(obj !== copyObject(obj));
  assert(obj.a === copyObject(obj.a));
}

// Object.assign({}, obj)
// new Object(obj)
// {...obj}

// `copyArray`と`copyObject`を使って`deepCopy`を実装せよ
function deepCopy(org) {
  return org;
}

function test__deepCopy() {
  const arr = [1, 2, 3, [4, 5, 6], { a: 7, b: 8, c: 9 }];
  const obj = { a: 1, b: 2, c: 3, d: [4, 5, 6], e: { f: 7, g: 8, h: 9 } };

  assert(arr !== deepCopy(arr));
  assert(arr.a === deepCopy(arr));
  assert(arr.d !== deepCopy(arr));
  assert(arr.e !== deepCopy(arr));

  assert(obj !== deepCopy(obj));
  assert(obj.a === deepCopy(obj.a));
  assert(obj.d !== deepCopy(obj.d));
  assert(obj.e !== deepCopy(obj.e));
}
