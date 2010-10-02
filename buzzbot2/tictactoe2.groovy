// This is the winning version of the 'bot
state=args[0]

// Define the slices through the state that
// correspond to the rows, columns and diagonals
//
// 0 | 1 | 2
// ---------
// 3 | 4 | 5
// ---------
// 6 | 7 | 8
//
// Groovy is pulling out the *contents* of the cells
r1=state[0..2]
r2=state[3..5]
r3=state[6..8]
c1=state[0,3,6]
c2=state[1,4,7]
c3=state[2,5,8]
d1=state[0,4,8]
d2=state[2,4,6]

// The first strategy is to complete any
// line that only has one empty cell
//
// Since we haven't worked out whether we're
// playing as 0 or x we might win or block
// - an improvement would call the same strategy
//   to try and win first, then block.
//
// At a code level, letting playNow() inspect the
// contents of 'state' itself would remove the
// duplication of the row/column/diagonals defs.
//
// N.B. Calling play() prints a cell and exits
//
if (playNow(r1)) play([0,1,2])
if (playNow(r2)) play([3,4,5])
if (playNow(r3)) play([6,7,8])
if (playNow(c1)) play([0,3,6])
if (playNow(c2)) play([1,4,7])
if (playNow(c3)) play([2,5,8])
if (playNow(d1)) play([0,4,8])
if (playNow(d2)) play([2,4,6])


// The second strategy (and the one we played in the 
// first iteration) is to work through our preferred
// cells and play the first one that's free
play([4,0,8,2,6,5,3,1,7])


// Play our turn, choosing the first of the provided
// list of choices that's free, then exit (ugly but easy)
//
def play(order) {
	for (i in order)  {
	   if (state[i] == '-')  {
		println i 
		break
	   }
	}
        System.exit(0)
}

// Boolean: do we want to play the line supplied ?
// Two of 0 or x and a dash means 'yes'
def playNow(line) {
	twoOfOne(line) && dashCount(line) == 1
}

// Boolean: Are there two of either 0 or x in the supplied line?
def twoOfOne(line) {
	(oCount(line) == 2) || (xCount(line) == 2)
}

def dashCount(line) {
	charCount(line, '-')
}
def oCount(line) {
	charCount(line, '0')
}
def xCount(line) {
	charCount(line, 'x')
}

// How many of the specified character are on the
// supplied line ?
// (We couldn't figure out the regex on-liner in time ;-) 
def charCount(line, ch) {
	def count=0
	line.each { if (it == ch) count++}
	count
}
