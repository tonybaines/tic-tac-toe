state=args[0]
// The first version of the bot
// play based on our prefered order of cells
order=[4,0,8,2,6,5,3,1,7]

// Pick the first free cell
for (i in order)  {
   if (state[i] == '-')  {
	println i 
	break
   }
}


