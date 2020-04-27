# Running the CLI

## Deployment
    1. Navigate to where the file is located on your computer in the console
    2. ./gradlew build
    3. ./gradlew shadow
    4. java -jar path/to/jar --cli (for cli) or java -jar path/to/jar (for GUI)
    5. To switch between the GUI and CLI, save, exit, and relaunch the application
       in the desired mode. 

    The default option will open up the GUI application. 

# CLI commands:
              addMethod:           adds a method to the specified class
              addField:            adds a method to the specified class
              addRelationship:     adds a relationship between the specified classes
              deleteClass:         deletes the specified class
              deleteMethod:        deletes a method from the specified class
              deleteField:         deletes a field from the specified class
              deleteRelationship:  deletes the relationship from the specified classes
              editClass:           changes the name of the class to the specified new name
              editMethod:          changes the name of the method to the specified new name
              editField:           changes the name of the field to the specified new name
              list:                displays all class names
              save:                saves project information to the specified file path
              load:                loads project information from the specified file path
              help:                can either show all command information or information about a specific command
              png:                 saves the project as a png file
              
# GUI usage:

   ## Add a class:
                    1. To add a class, begin by clicking the 'add class' button.
                       A new window will appear, prompting for information
                       about the class.
                    2. Enter the name of the class you would like to add,
                       then click to blue '+' button to confirm. You may add 
                       methods and fields to the class here, as well as delete
                       methods and fields by clicking the red '-' button.
                       *Note: In order to add a relationship, there must be at least
                       two classes.
                    3. When finished adding the class information, press the 
                       "apply" button to close the add class window and create
                       a UML diagram. 
       
  ## Edit a class:
                    1. To edit a class, begin by clicking the 'edit class' button or 
                       by selecting the existing class node you wish to edit.
                    2. Enter the new name of the class, then click to red '+' button to confirm. 
                       You may also edit methods, fields, and relationships to the class here, 
                       as well as delete methods, fields, and relationships by clicking the
                       blue '-' button.
                    3. When finished editing the class information, press the 
                       "apply" button to close the edit class window and update
                       the UML diagram.       
                       
  ## Delete a class:
                    1. To delete a class, begin by clicking the 'delete class' button or 
                       by selecting the existing class node you wish to delete.
                    2. Select the desired item you wish to delete in the list
                       of class elements, and press the blue '-' button to
                       confirm your choice.
                    3. When finished, press the "apply" button to close the 
                       delete class window and update the UML diagram.       
                       
  ## Edit UML diagram:
                    1. To move the UML diagram, simply click and drag it 
                        to the desired position. 
                     
                     
  ## Save a project:
                    1. To save the current project information, click the 'save'
                       button. 
                    2. A file explorer will appear, prompting for
                       a name and a choice of file type and where to save the file.
                    3. Enter the name you wish to save the file as
                       and navigate to where the file should be saved
                       and click the save button.
                       
  ## Load a project:
                    1. To load a project, click the 'load'
                       button. 
                    2. A file explorer will appear, where you
                       can select the file that you would like
                       to load.
