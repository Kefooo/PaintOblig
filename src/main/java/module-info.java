module com.example.demopaint {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demopaint to javafx.fxml;
    exports com.example.demopaint;
}