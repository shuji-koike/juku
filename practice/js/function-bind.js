// `Function#apply`と`arguments`をつかってbindを実装せよ
function oldBind() {}

// `Function#apply`と'rest parameter syntax'をつかってbindを実装せよ
function newBind() {}

function test() {
  const arr = [1, 2, 3];
  const push = arr.push;

  // push(4, 5, 6);
  // > TypeError: Cannot convert undefined or null to object

  const a1 = arr.slice();
  a1.push(4, 5, 6, 7, 8, 9);
  console.log(a1);

  const a2 = arr.slice();
  push.bind(a2, 4, 5, 6)(7, 8, 9);
  console.log(a2);

  const a3 = arr.slice();
  oldBind(push, a3, 4, 5, 6)(7, 8, 9);
  console.log(a3);

  const a4 = arr.slice();
  newBind(push, a4, 4, 5, 6)(7, 8, 9);
  console.log(a4);

  const a5 = arr.slice();
  const push2 = e => push(e);
  push2(4, 5, 6, 7, 8, 9);
  console.log(a5);
}

// links
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function/bind
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function/apply
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/arguments
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/rest_parameters
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Spread_syntax
