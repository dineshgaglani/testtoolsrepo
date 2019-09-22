package propertiesbuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Properties;

/**
 * Created by dgaglani on 12/5/2017.
 */
public class ModuleDriver {

    public static void main(String args[]) throws IOException {
        List<Class> classes = ClassReader.getClassesInPackage("pages");

        for (Class clazz : classes) {
            File createdPropertyFile = PropertyUtil.createPropertyFileObjectForClass(clazz);
            List<Field> mobileElementFields = ClassReader.parseClassFields("org.openqa.selenium.WebElement", clazz);
            if (!createdPropertyFile.exists()) {
                createdPropertyFile.createNewFile();
            }
            Properties pageObjectProperties = PropertyUtil.getPropertiesFromFile(createdPropertyFile.getPath());
            for (Field mobileElementField : mobileElementFields) {
                FileOutputStream output = new FileOutputStream(createdPropertyFile);
                PropertyUtil.createPropertyForField(mobileElementField, pageObjectProperties);
                pageObjectProperties.store(output, "Generated property");
            }

        }
    }

}
