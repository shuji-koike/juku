import React from "react";
import ReactDOM from "react-dom";
import "./index.css";

ReactDOM.render(<h1>Juku!</h1>, document.querySelector("body > main"));

const arr = [1, 2, 3];
const obj = { a: 1, b: 2, c: 3 };

[{ uid: "aaa" }, { uid: "bbb" }, { uid: "ccc" }].map((e) => e.uid);

[1, 2, 3].every((e) => e % 2);
[1, 2, 3].some((e) => e % 2);

[1, 2, 3].reduce((acc, e) => acc + e);

[1, 2, 3].filter((e) => e % 2);
[1, 2, 3].reduce((acc, e) => (e % 2 ? acc.concat(e) : acc), []);
[1, 2, 3].reduce((acc, e) => [...acc, e], []);
["a", "b", "c"].reduce((acc, e) => acc + "," + e);
// [1, 2, 3]
// [], 1 => [1]
// [1], 2 => [1]
// [1,2], 3 => [1,3]

// ["a", "b", "c"].join()
// "a,b,c"

// "a" , "b" => "a" + "," + "b"
// "a,b" , "c" => "a,b" + "," + "c"

["a", "a", "b", "b", "b", "c"].reduce<{ [key: string]: number }>((acc, e) => {
  acc[e] = 1 + (acc[e] || 0);
  return acc;
}, {});

["a", "a", "b", "b", "b", "c"].reduce<{ [key: string]: number }>(
  (acc, e) => ({ ...acc, [e]: 1 + (acc[e] || 0) }),
  {}
);

// ["a", "a", "b", "b", "b", "c"]
// {a: 2, b: 3, c: 1}

// {}
// {}, "a" => acc["a"] = 1 + 0 => {a: 1}
// {a: 1}, "a" => acc["a"] = 1 + 1 => {a: 2}
// {a: 2}, "b" => {a: 2, b: 1}

for (const key in arr) {
  console.log(key);
}

[1, 2, 3].map((x) => x * 2);

// class Object {
//   toString() {}
//   get __proto__() {}
// }

class Animal {
  constructor() {
    console.log("new Animal");
  }
}

class Cat extends Animal {
  name: string;
  getName() {
    return this.name;
  }
  constructor(name: any) {
    super();
    this.name = name;
  }
}

function NewCat(name: string) {
  return { name };
}
NewCat("tama");
new Cat("tama");

const tama = new Cat("tama");
tama.toString();

const dora = { name: "dora" };
Object.setPrototypeOf(dora, Cat.prototype);
console.log((dora as any).getName());

// {name: "tama", getName: function(){return this.name}}

// [["name", "tama"]]
console.log(Object.entries(tama));

console.log(Object.getPrototypeOf(tama));
tama.getName();

console.log([tama]);

const tama2 = copyObject(new Cat("tama"));
// console.log(tama2.getName());
// [{name:"tama"}]

// console.log([1, 2, 3]);
console.log(copyObject(["a", "b"]));

// https://developer.mozilla.org/ja/docs/Web/JavaScript/Enumerability_and_ownership_of_properties

// [
//   ["a", 1],
//   ["b", 2],
//   ["c", 3],
// ].forEach((x) => {
//   console.log(x);
// });

// [
//   ["a", deepCopy(1)],
//   ["b", deepCopy(2)],
//   ["c", deepCopy(3)]
// ];

// `for`文を使ってarrayのコピーを返すfunctionを実装せよ
function copyArray(arr: any[]) {
  const copy = [];
  for (let i = 0; i < arr.length; i++) {
    copy[i] = arr[i];
  }
  return copy;
}

// `for in`文を使ってobjectのコピーを返すfunctionを実装せよ
function copyObject(obj: any) {
  const copy: any = {};
  for (const key of Object.getOwnPropertyNames(obj)) {
    copy[key] = obj[key];
  }
  Object.setPrototypeOf(copy, obj.__proto__);
  return copy;
}

console.log(copyArray(arr));
console.log(arr === copyArray(arr));
console.log(copyObject(obj));

function deepCopy(t: any): any {
  if (t instanceof Array) {
    return copyArray(t).map(deepCopy);
  } else if (typeof t === "object") {
    return Object.entries(copyObject(t))
      .map(([key, value]) => [key, deepCopy(value)])
      .reduce(
        (acc, [key, value]) => ({
          ...acc,
          [key as string]: value,
        }),
        {}
      );
  } else if (typeof t === "function") {
    throw "error";
  }
  return t;
}

/*
deepCopy([1, 2, 3]);
deepCopy({ a: 1, b: 2 });

function test__deepCopy() {
  const obj = { a: 1, b: 2, c: 3, d: [4, 5, 6], e: { f: 7, g: 8, h: 9 } };

  assert(obj !== deepCopy(obj));
  assert(obj.a === deepCopy(obj.a));
  assert(obj.d !== deepCopy(obj.d));
  assert(obj.e !== deepCopy(obj.e));
  assert(obj.e.f === deepCopy(obj.e.f));

  console.log("ok!!!");
}

function assert(testcase: boolean) {
  if (!testcase) {
    throw "fail";
  }
}

test__deepCopy();
*/
