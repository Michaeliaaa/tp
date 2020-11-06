---
layout: page
title: Kenneth's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop app that allows doctors to manage patient records and schedule upcoming appointments.

Given below are my contributions to the project.

* **New Feature**: Added the ability to add profile pictures.
  * What it does: Allows the user to add a profile picture to each patient profile.
  * Justification: This feature improves the product significantly because the user can now quickly identify patients using their profile pictures, instead of relying on other forms of identification
                   such as names which can be duplicated.
  * Highlights: User can use their mouse to drag and drop profile pictures onto the patient's profile. This makes it easier for the user to add profile pictures, without having
                to waste time specifying the image's filepath.
  * Credits: 
    1. Manipulation of images in JavaFX: https://www.tutorialspoint.com/javafx/javafx_images.htm
    2. Implement drag and drop feature: https://examples.javacodegeeks.com/desktop-java/javafx/event-javafx/javafx-drag-drop-example/,
       https://www.genuinecoder.com/drag-and-drop-in-javafx-html/

* **New Feature**: Added a profile display command.
  * What it does: Displays the patient's profile on a separate window.
  * Justification: Allows user to view the patient's profile and access relevant details such as Blood Type, Address, etc.
  * Highlights: This command displays the patient's profile on a separate popup window, which allows the user to focus on one individual patient. This helps
                to declutter the main window.
  * Credits: 
    1. Implement popup windows: https://www.geeksforgeeks.org/javafx-popup-class/, https://quollwriter.wordpress.com/2019/04/08/how-to-create-a-popup-pane-in-javafx/,
                          https://github.com/AY1920S1-CS2103T-F12-2/main, https://gist.github.com/jewelsea/1926196,
    2. Populate separate panel with custom list: https://docs.oracle.com/javafx/2/ui_controls/list-view.htm
    3. Custom list cell: https://stackoverflow.com/questions/25246496/javafx-custom-list-cell-updateitem-being-called-a-lot
    2. Existing AB3 codebase: https://github.com/se-edu/addressbook-level3
    
* **New Feature**: Added the ability to add a new visitation log.
  * What it does: Adds a new visit for the specified patient.
  * Justification: This features improves the product significantly because the user can now record visitation details (eg. Diagnosis) and store them in CliniCal.
  * Highlights: User can enter relevant details about the visitation in a separate popup window. This helps to declutter the main window.
  * Credits: 
    1. Existing AB3 codebase: https://github.com/se-edu/addressbook-level3
    
* **New Feature**: Enhanced the find command to allow partial matching, and included IC number as an additional search parameter
  * What it does: Locates a patient after the user specifies either the patient's name or IC number.
  * Justification: User can quickly locate a specific patient by searching the patient's unique IC number. This prevents searching of patients
                   with duplicated names.
  * Highlights: Enhanced partial matching functionality allows user to type in the first few digits of the patient's IC number, or the first few characters of the patient's name to 
                locate the patient, instead of having to specify the entire IC number or name.
  * Credits: NIL
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=afroneth)

* **Project management**:
  * Managed release `v1.2.1` on GitHub

* **Enhancements to existing features**:
  * Assisted in styling of GUI ([\#149](), [\#233]())
  * Wrote additional tests for `ParserUtil`, `CommandResult`, `LogicManager` classes. ([\#164](), [\#168](), [\#169]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addpicture`, `addvisit` and `profile` ([\#148]())
  * Developer Guide:
    * Added implementation details of the `addpicture`, `addvisit` and `profile`feature ([\#83]())
    * Added new use cases for `addpicture` functionality. ([\#83]())

* **Community**:
  * Reported bugs and suggestions for other teams in the class (during Practical Dry Run): [Issues link](https://github.com/afroneth/ped/issues)

* **Tools**:
  * Integrated a third party library (Commons IO) to the project ([\#61]())
