import java.util.ArrayList;

public class Lists {

    public static void swapLargestAndSecondSmallest(ArrayList<Integer> a)
    {
        int largest = 0;
        int smallest;
        int secondSmallest;

/*	      if(a.size() > 1){

	        for(int i = 0; i < a.size(); i++){
	            if(a.get(largest) < a.get(i)) largest = i;
	        }*/

        if(a.size()>1) {

            if(a.get(0) > a.get(1)){
                smallest = 1;
                secondSmallest = 0;
            } else {
                smallest = 0;
                secondSmallest = 1;
            }

            for(int i = 0; i < a.size(); i++){

                if(a.get(largest) < a.get(i)) largest = i;

                if(a.get(i) < a.get(secondSmallest)){
                    if (a.get(i) < a.get(smallest)){
                        secondSmallest = smallest;
                        smallest = i;
                    }
                    else if(a.get(i) == a.get(smallest)) {}
                    else secondSmallest = i;
                }

//                    if (a.get(i) < smallest) {
//                    secondSmallest = smallest;
//                    smallest = a.get(i);
//                } else if (a.get(i) < secondSmallest) {
//                    secondSmallest = a.get(i);
//                }
            }

//            if(secondSmallest != smallest) {
//
//                for(int i = 0; i < a.size(); i++){
//
//                    if (a.get(i) == secondSmallest) {
//                        secondSmallest = i;
//                        break;
//                    }
//
//                }

                int temp = a.get(largest);
                a.set(largest, a.get(secondSmallest));
                a.set(secondSmallest, temp);
//            }
        }
    }
}

