'use strict'

const createHashSet = require('./hashset')

const set1 = createHashSet(10, hashCode, equals)
const set = createHashSet(15, hashCode, equals)

function hashCode (y) {
  let h = 0
  let x = y.toString()

  for (let i = 0; i < x.length; i++) {
    h = ((h << 5) - h) + x.charCodeAt(i)
    h = h & h
  }

  return h
}

function equals (y, x) {
  let x1 = hashCode(x)
  let x2 = hashCode(y)

  return x1 === x2
}

test('contains', () => {
  set1.add(5)
  set1.add(7)
  expect(set1.contains(5)).toBe(true)
  expect(set1.contains(100)).toBe(false)
  expect(set1.size()).toBe(2)

  set1.add(10)
  expect(set1.size()).toBe(3)

  expect(set1.contains(7)).toBe(true)
  expect(set1.contains(4)).toBe(false)

  expect(set1.remove(4)).toBe(false)
  expect(set1.remove(5)).toBe(true)
  expect(set1.contains(5)).toBe(false)
})

test('iter', () => {
  set.add(0)
  set.add(1)
  set.add(2)
  set.add(3)
  set.add(4)

  expect(set.size()).toBe(5)

  let a = set.iterator()

  expect(a.hasNext()).toBe(true)
  expect(a.next()).toBe(0)
  expect(a.next()).toBe(1)
  expect(a.next()).toBe(2)
  expect(a.next()).toBe(3)
  expect(a.next()).toBe(4)
  expect(a.hasNext()).toBe(false)

  let b = set.iterator()

  expect(b.hasNext()).toBe(true)
  expect(b.next()).toBe(0)
  expect(b.next()).toBe(1)

  expect(set.size()).toBe(5)
  expect(set.contains(1)).toBe(true)
  b.remove()
  expect(set.contains(1)).toBe(false)
  expect(b.next()).toBe(2)
  b.remove()
  expect(set.contains(2)).toBe(false)
})
