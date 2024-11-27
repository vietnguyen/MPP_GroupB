The Order class is the owner of the association. This means that the Order class is responsible for the creation and deletion of the OrderLine object. The Order class will have a reference to the OrderLine object. The OrderLine class can not be instantiated without an Order object.
This is why the constructor of the OrderLine class has package visibility. This means that only classes in the same package can instantiate the OrderLine object.