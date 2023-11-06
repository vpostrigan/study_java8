// MyShapeControllerFactory.java
// MyShapeControllerFactory uses the Factory Method design
// pattern to create an appropriate instance of MyShapeController
// for the given MyShape subclass. 
package advanced_java2_deitel.chapter5_graphic_with_mvc.controller;

import advanced_java2_deitel.chapter5_graphic_with_mvc.model.DrawingModel;
import advanced_java2_deitel.chapter5_graphic_with_mvc.model.shapes.MyShape;

public class MyShapeControllerFactory {

    private static final String FACTORY_PROPERTY_KEY =
            "MyShapeControllerFactory";

    private static final String[] supportedShapes =
            {"MyLine", "MyRectangle", "MyOval", "MyText"};

    // reference to Singleton MyShapeControllerFactory
    private static MyShapeControllerFactory factory;

    protected MyShapeControllerFactory() {
    }

    // return Singleton instance of MyShapeControllerFactory
    public static final MyShapeControllerFactory getInstance() {
        // if factory is null, create new MyShapeControllerFactory
        if (factory == null) {

            // get System property that contains the factory
            // class name
            String factoryClassName =
                    System.getProperty(FACTORY_PROPERTY_KEY);

            // if the System property is not set, create a new
            // instance of the default MyShapeControllerFactory
            if (factoryClassName == null)
                factory = new MyShapeControllerFactory();

                // create a new MyShapeControllerFactory using the
                // class name provided in the System property
            else {

                // create MyShapeControllerFactory subclass instance
                try {
                    factory = (MyShapeControllerFactory)
                            Class.forName(factoryClassName).newInstance();
                } catch (ClassNotFoundException classException) {
                    classException.printStackTrace();
                } catch (InstantiationException exception) {
                    exception.printStackTrace();
                } catch (IllegalAccessException accessException) {
                    accessException.printStackTrace();
                }
            }
        }

        return factory;
    }

    // create new MyShapeController subclass instance for given
    // suitable for controlling given MyShape subclass type
    public MyShapeController newMyShapeController(
            DrawingModel model, String shapeClassName) {
        // create Class instance for given class name and
        // construct appropriate MyShapeController
        try {
            // get Class object for selected MyShape subclass
            Class shapeClass = Class.forName(
                    MyShape.class.getPackage().getName() + "." + shapeClassName);

            // return appropriate controller for MyShape subclass
            if (shapeClassName.equals("MyLine"))
                return new MyLineController(model, shapeClass);
            else if (shapeClassName.equals("MyText"))
                return new MyTextController(model, shapeClass);
            else
                return new BoundedShapeController(model, shapeClass);
        } catch (ClassNotFoundException classException) {
            classException.printStackTrace();
        }
        return null;
    }

    // get String array of MyShape subclass names for which this
    // factory can create MyShapeControllers
    public String[] getSupportedShapes() {
        return supportedShapes;
    }
}
