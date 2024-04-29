# CRUD de Personas en JavaFX

Proyecto para la asignatura de Entornos de Desarrollo, que consiste en desarrollar un CRUD (Crear, Leer, Actualizar, Eliminar) de personas utilizando JavaFX. Este proyecto permite gestionar información sobre personas, incluyendo la posibilidad de agregar nuevos registros, modificar los existentes, eliminar personas y ver los detalles de cada una de ellas. El objetivo es proporcionar una interfaz intuitiva y fácil de usar para el manejo de la información de las personas, además de manejar adecuadamente los posibles errores que puedan surgir durante las operaciones de CRUD.




## Funciones de `PeopleController`

#### `void initialize()`

Inicializa el controlador estableciendo las columnas de la tabla de personas.

#### `private Person getSelectedPerson()`

Obtiene la persona seleccionada en la tabla de personas.

#### `private void refreshTable()`

Actualiza la tabla de personas con la lista actualizada de personas. Se ha añadido un parámetro opcional para poder pasarle la lista de personas que se quiera mostrar en la tabla.

#### `private Person setModalScene(Person person)`

Abre un cuadro de diálogo modal para agregar o modificar una persona. Si se proporciona una persona existente, se abre el diálogo modal para modificarla. Si no se proporciona ninguna persona, se abre el diálogo modal para agregar una nueva persona.

#### `void addPerson()`

Maneja el evento de agregar una nueva persona. Abre el diálogo modal para ingresar los detalles de la nueva persona y la agrega a la lista de personas si se proporcionan los detalles válidos.

#### `void delete()`

Maneja el evento de eliminar una persona seleccionada. Elimina la persona seleccionada de la lista de personas y actualiza la tabla de personas.

#### `void modify()`

Maneja el evento de modificar una persona seleccionada. Abre el cuadro de diálogo modal para modificar los detalles de la persona seleccionada y actualiza la lista de personas si se guardan los cambios.

#### `void filterTable()`
Maneja el evento de escribir en el campo de búsqueda. Filtra la tabla de personas según el texto ingresado en el campo de búsqueda.

#### `private Boolean checkFilter(Person person)`
Comprueba si la persona cumple con el filtro de búsqueda. Devuelve true si la persona cumple con el filtro y false en caso contrario.




## Funciones de `ModalController`

#### `void initAttributes(ObservableList<Person> people)`

Inicializa los atributos del controlador con la lista de personas proporcionada. Utilizado para la operación de agregar una nueva persona.

#### `void initAttributes(ObservableList<Person> people, Person person)`

Inicializa los atributos del controlador con la lista de personas proporcionada y una persona existente para editar. Utilizado para la operación de editar una persona existente.

#### `private void closeWindow()`

Cierra la ventana modal actual.

#### `private Person getInputPerson()`

Obtiene los datos ingresados por el usuario desde los campos de entrada de la ventana modal y los convierte en un objeto de tipo Person. Si algún campo obligatorio está vacío o si la edad no es un número entero, muestra un mensaje de error y devuelve null.

#### `void savePerson()`

Guarda la nueva persona ingresada o actualiza los detalles de la persona existente en la lista de personas. Muestra mensajes de éxito o error según corresponda.

#### `Person getPerson()`

Devuelve la persona modificada o agregada.

#### `void exit()`

Restablece el objeto person a null y cierra la ventana modal.




## Funciones de `Utils`

#### `static void showAlert(String title, String message, Alert.AlertType type)`




Muestra un mensaje de alerta con el título, el contenido y el tipo especificados. Utiliza un cuadro de diálogo Alert de JavaFX para mostrar el mensaje. Además, agrega un icono al cuadro de diálogo para proporcionar una mejor experiencia visual al usuario.
## Autor

- [@marioperdiguero](https://github.com/MarioPB05)

