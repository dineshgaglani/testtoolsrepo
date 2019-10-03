package javatricks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dinesh on 9/25/2019.
 */
public class ListTricks {

    class Animal {
       Integer limbs;
       String sound;
    }

    class Horse extends Animal {

    }


    class Dog extends Animal {

    }

    public static void main(String args[]) {
//        trial and error - don't analyse
        ListTricks o = new ListTricks();
//        List<Animal> horse = new ArrayList<>(Arrays.asList(new Animal[] {o.new Horse()} ));
//        List<Animal> dogs = new ArrayList<>(Arrays.asList(new Animal[] {o.new Dog()} ));
//        horse.set(1, dogs.get(0));
    }
}
