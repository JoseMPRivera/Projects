'use strict'

function Link () {
  let data
  let next

  return {

    data: data,
    next: next
  }
}

function createHashSet (bucketsLength, hashCode, equals) {
  var buckets = []
  var size = 0
  var current = null

  for (let i = 0; i < bucketsLength; i++) {
    buckets.push(null)
  }

  return {

    contains: (x) => {
      let h = hashCode(x)

      if (h < 0) h = -h
      h = h % buckets.length

      let current = new Link()
      current = buckets[h]

      while (current != null) {
        if (equals(current.data, x)) return true
        current = current.next
      }
      return false
    },

    add: (x) => {
      let h = hashCode(x)
      if (h < 0) h = -h
      h = h % buckets.length

      let current = new Link()

      current = buckets[h]
      while (current != null) {
        if (equals(current.data, x)) { return false } // already in the set
        current = current.next
      }
      let newLink = new Link()
      newLink.data = x
      newLink.next = buckets[h]
      buckets[h] = newLink
      size++
      return true
    },
    size: () => {
      return size
    },

    remove: (x) => {
      let h = hashCode(x)
      if (h < 0) h = -h
      h = h % buckets.length

      let current = new Link()
      let previous = new Link()
      current = buckets[h]
      previous = null
      while (current != null) {
        if (equals(current.data, x)) {
          if (previous == null) buckets[h] = current.next
          else previous.next = current.next
          size--
          return true
        }
        previous = current
        current = current.next
      }
      return false
    },

    iterator: () => {
      var bucket = -1
      var previous = null
      var previousBucket = -1

      // while (bucket < bucketsLength && !buckets[bucket]) {
      // 	bucket++
      //   }

      //   if (bucket < bucketsLength) {
      // 	current = buckets[bucket];
      //   } else {
      // 	current = null;
      //   }

      return {

        hasNext: () => {
          if (current !== null && current.next !== null) {
            return true
          }
          for (let b = bucket + 1; b < bucketsLength; b++) {
            if (buckets[b] != undefined) {
              return true
            }
          } return false
        },

        next: () => {
          previous = current

          previousBucket = bucket
          if (current == null || current.next == null) {
            bucket++

            while (bucket < buckets.length && buckets[bucket] == null) { bucket++ }
            if (bucket < buckets.length) { current = buckets[bucket] } else {
              throw ('Illegal State Exception')
            }
          } else // move to next element in bucket
          { current = current.next }
          return current.data
        },

        remove: () => {
          if (previous != null && previous.next == current) {
            previous.next = current.next
          } else if (previousBucket < bucket) { buckets[bucket] = current.next } else { throw ('Illegal State Exception') }
          current = previous
          bucket = previousBucket
        }
      }
    }
  }
}

module.exports = createHashSet
