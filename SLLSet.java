public class SLLSet{

    //instance variables//
    private int size;
    private SLLNode first;

    //contructor 1//
    public SLLSet()
    {
        this.first = null; //initialize the first node to null
        this.size = 0; 
    }

    //contructor 2//
    public SLLSet(int [] sortedArray)
    {
        this.first = null; 
        this.size = 0;
        if(sortedArray != null)
        {
            for(int i = sortedArray.length - 1 ; i >= 0; i--) //loop through list backwards
            {
                SLLNode current = new SLLNode(sortedArray[i], this.first); //make every new element the first element
                this.first = current; //this makes the new element the first
                this.size++; //increment size
            }
        }
    }

    //getSize//
    public int getSize()
    {
        // int size = 0;
        // SLLNode temp = this.first;
        // while(temp != null)
        // {
        //     size++;
        //     temp = temp.next; 
        // }
        int size = this.size; 
        return size; //returns size
    }



    //copy//
    public SLLSet copy()
    {
        int list[] = new int[this.size]; //create an empty list of the size of this SLL
        SLLNode temp = this.first; //create a temporary modifier
        for(int i = 0; i < this.size; i++) //run a forloop to go through the list
        {
            list[i] = temp.value; //initialize value at list[i] to value of temp
            temp = temp.next; //loop through temp SLL
        }
        SLLSet copy = new SLLSet(list); //create a new SLLSet via contructor 2
        return copy; //return this copy
    }

    

    //isIn//
    public boolean isIn(int v)
    {
        SLLNode temp = this.first; //create a temp modifier node
        while(temp != null) //while the node is not null
        {
            if(temp.value == v) //if the value of v matches this.value at the node
            {
                return true; //return true
            }
            temp = temp.next; //loop through temp
        }
        return false; //return false otherwise
    }


    //add//
    public void add(int v)
    {

        if(isIn(v) == false) //we dont want to repeat numbers so check if its in. 
        {
            //for beginning of list//
            if(this.first == null || v < this.first.value)
            {
                SLLNode begin = new SLLNode(v, null);
                begin.next = this.first; //change the next value to the previos first//
                this.first = begin; //change first to new begin//
                this.size++; //increment size
                return; 
            }
            //for adding in middle list//
            for(SLLNode temp = this.first; temp != null; temp = temp.next) //could have also used while loop// 
            {
               if(temp.next != null && ((v > temp.value) && (v < temp.next.value)))
               {
                   SLLNode mid = new SLLNode(v, null);
                   mid.next = temp.next; //change the new nodes next to the previous nodes next
                   temp.next = mid; //change the previous nodes next to the new node//
                   this.size++; //increment the size
                   return;
               } 
               //for end of list
               else if(temp.next == null)
               {
                   SLLNode end = new SLLNode(v, null);
                   //end.next = temp.next; //this line is copied from mid, but not needed because end.next is already null
                   temp.next = end; //change the old last index to point at the new end
                   this.size++; //increment size
                   return;
               }
            }
        }
    }

    //remove//
    public void remove(int v)
    {
        if(isIn(v)) //check if v is in the list
        {
            //if v is the first
            if(v == this.first.value)
            {
                this.first = this.first.next; //change first to whatever was next
                this.size--; //increment
                return; //return void
            }
            //for removing something from middle and end
            for(SLLNode temp = this.first; temp != null; temp = temp.next) //for loop to loop through linked list
            {
                if(v == temp.next.value)
                {
                    temp.next = temp.next.next; //skips v, gets collected by garbage 
                    this.size--;
                    return;
                }
            }
        }
        else{return;}
    }
    
    //union//
    public SLLSet union(SLLSet s)
    {
        //this is for inefficient union
        //SLLSet union = new SLLSet(); //create a SLL 

        // for(SLLNode temp = this.first; temp != null; temp = temp.next)
        // {
        //     union.add(temp.value); //add the first SLL via add method
        // }
        // for(SLLNode temp1 = s.first; temp1 != null; temp1 = temp1.next)
        // {
        //     union.add(temp1.value); //add the second via add
        // }
        // return union; //note: add() automatically checks if the value is already in the SLL 

        //efficient union
        SLLSet union = new SLLSet(); //create empty SLL union
       
        SLLNode union_first = new SLLNode(0,null); //create a Node that will point at the beginning of Union SLL
        SLLNode temp = union_first; //create a modifier for the node
        SLLNode temp1 = this.first; //iterator for this
        SLLNode temp2 = s.first; //iterator for s

        //efficient union
        while(true) //have loop run untill its broken.
        {
            if(temp1 == null) //if this is empty
            {
                temp.next = new SLLNode(temp2.value, temp2.next); //then adds all only temp2 or s values
                temp2 = temp2.next; //iterate
                union.size ++; //increment
                if(temp2 == null)
                {
                    break; //once temp2 is null, and temp1 is null, the loop finally breaks
                }
                //break; //break loop;
            }
            else if(temp2 == null) //if s list is empty
            {
                temp.next = new SLLNode(temp1.value, temp1.next); //then unions next node is this first, or the remander of this
                temp1 = temp1.next;
                union.size ++;
                if(temp1 == null)
                {
                    break; //breaks once both temps are null
                }
            }
            else if(temp1.value < temp2.value) //if the value of this is lower than s
            {
                temp.next = new SLLNode(temp1.value, temp1.next); //temp.next is equal to a new node of these parameters
                temp1 = temp1.next; //iterate this
                union.size++;
            }
            else if(temp1.value == temp2.value) //if both values are same
            {
                temp.next = new SLLNode(temp1.value, temp1.next); //temp.next is equal to a new node of these parameters
                temp1 = temp1.next; //both this and s iterate
                temp2 = temp2.next;
                union.size++;
            }
            else //if s has larger value
            {
                temp.next = new SLLNode(temp2.value, temp2.next);
                temp2 = temp2.next; 
                union.size++;
            }

            temp = temp.next; //iterate temp
        }
        union.first = union_first.next; //union.first is equal to the node created earlier, where temp looped through this node
        return union; //returns union
    }

    //intersection//
    public SLLSet intersection(SLLSet s)
    {
        //this code is for doing intersection simply but inefficiently
        // SLLSet intersection = new SLLSet(); //create new SLL
        
        // for(SLLNode temp = this.first; temp != null; temp = temp.next) //loops through SLL
        // {
        //     if(s.isIn(temp.value)) //if the value from this SLL is in the s SLL, then...
        //     {
        //         intersection.add(temp.value); //add it to the new SLL
        //     }
        // }

        // return intersection; //return the SLL

        //efficient code
        SLLSet intersection = new SLLSet(); //create empty intersection
        
        SLLNode intersection_first = new SLLNode(0,null); //create a Node that will point at the beginning of Union SLL
        SLLNode temp = intersection_first; //create a modifier for the pointer
        SLLNode temp1 = this.first; //iterator for this
        SLLNode temp2 = s.first; //iterator for s

        if(temp1 == null || temp2 == null)
        {
            return intersection;
        }
        while(temp1 != null && temp2 != null) //have loop run untill its broken.
        {
            if(temp1.value < temp2.value)
            {
                temp1 = temp1.next;
            }
            else if(temp1.value > temp2.value)
            {
                temp2 = temp2.next;
            }
            else 
            {
                temp.next = new SLLNode(temp1.value, null);
                temp = temp.next;
                temp1 = temp1.next;
                temp2 = temp2.next;
                intersection.size++;
            }
        }
        intersection.first = intersection_first.next; //making intersections first node the next node of the old temporary node
        return intersection;
    }

    //Difference//
    public SLLSet difference(SLLSet s)
    {
        SLLSet difference = new SLLSet(); 
        for(SLLNode temp = this.first; temp != null; temp = temp.next)
        {
            if(s.isIn(temp.value) == false) //checks if this value is not in s SLL, if it isnt...
            {
                difference.add(temp.value); //add it to the difference SLL
            }
        }
        return difference;
    }

    //Static Union//
    public static SLLSet union(SLLSet[] sArray)
    {
        SLLSet static_union = new SLLSet(); //create empty union SLL

        for(int i = 0; i < sArray.length; i++) //loop through every list
        {
            static_union = static_union.union(sArray[i]); //the ith SLL list is unioned with the static_union
        }
        return static_union; //return the SLL
    }



    //toString//
    public String toString()
    {
        SLLNode temp = this.first; 
        String output = new String(); // creates an empty string
        while (temp != null)  
        { 
            output += temp.value + ""; //add the value to string
            if(temp.next != null)
            {
                output += ", "; //add the comma if and only if it is not the last element
            }
            temp = temp.next; 
        } 
        return output;
    }

}

//node class
class SLLNode{
    int value;
    SLLNode next;
    public SLLNode(int i, SLLNode n)
    {
        value = i;
        next = n; 
    }
}

