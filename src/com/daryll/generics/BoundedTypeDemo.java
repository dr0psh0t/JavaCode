package com.daryll.generics;

import com.daryll.abstractclassesinterfaces.Circle;
import com.daryll.abstractclassesinterfaces.GeometricObject;
import com.daryll.abstractclassesinterfaces.Rectangle;

public class BoundedTypeDemo {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(2, 2);
        Circle circle = new Circle(2);

        System.out.println("Same area? "+equalArea(rectangle, circle));
    }

    //  'E' is a generic subtype of GeometricObject. should only be bound to GeometricObject.
    public static <E extends GeometricObject> boolean equalArea(
            E object1, E object2) {

        return object1.getArea() == object2.getArea();
    }
}