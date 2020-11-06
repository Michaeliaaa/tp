---
layout: page
title: Geon Sik Moon's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop application that allows doctors to manage patient records and schedule upcoming appointments. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

**New Implementation**: Implemented Appointment.
  * What it does: Appointment stores the details of an upcoming appointment with a patient, including patient name, patient NRIC, appointment time, and appointment duration.
  * Justification: This implementation allows the user to schedule upcoming appointments and prevent any time conflicts. Appointment objects are also integrated with calendar to allow the user to view the weekly schedule.
  * Highlights: This implementation is fundamental for managing patient appointments, which is one of the main features of the application. It required an extensive understanding of the jfxtras library and its components. The implementation too was challenging as Appointment had to be converted to VEvent objects for integration with the calendar feature.
  * Credits:
    1. For understanding of iCalendar : [iCalendar.org](https://icalendar.org)
    
**New Implementation**: Implemented JSON Serialization for Appointment.
  * What it does: Appointment objects are serialized into json file format and clinical.json is deserialized into Appointment objects.
  * Justification: This implementation allows the user to automatically save the appointments in json file format and also to load the previously saved appointments when the application is restarted.
  * Highlights: This implementation is fundamental for saving and loading patient appointments, which is one of the main features of the application. It required an in-depth understanding of JSON serialization.
  * Credits:
    1. For implementation of storage: [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)
    
**New Implementation**: Added new field objects for patients.
  * What it does: New patient field objects allow the user to manage the extensive details of a patient, including NRIC, sex, blood type, and allergies.
  * Justification: This addition improves user's management of patient records because a user can store and view patient details that are essential for consultations.
  * Highlights: This enhancement affects existing and future Patient commands. It required an in-depth understanding of regular expression(regex) to check the validity of the patient fields. The implementation required careful planning as it required changes to existing commands.
  * Credits:
    1. For implementation of Allergy: [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)
    
**New Implementation**: Implemented Visit and VisitHistory.
  * What it does: Visit stores the details of a patient's visit, including visit date, patient name, diagnosis, prescription, and comment. VisitHistory stores the list of visits for each patient.
  * Justification: This implementation enhances user's management of patient records since a user can store details of a patient visit and refer to past visits for more accurate diagnosis.
  * Highlights: This implementation is fundamental for managing patient visit records, which is one of the main features of the application.

**New Feature**: Added the ability to edit appointments.
  * What it does: The new feature allows the user to edit details of existing appointments.
  * Justification: This feature improves user's management of appointments because a user can make mistakes in scheduling appointments and the app should provide a convenient way to rectify them.
  * Highlights: This implementation required a careful consideration of design alternatives and validity checking mechanisms as schedule conflicts and addition of overdue appointments had to be prevented, based on the current time.
  * Credits:
    1. For implementation of EditAppointmentCommand: [AddressBook Level-3](https://github.com/se-edu/addressbook-level3)

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=gsmoon97)

* **Project management**:
  * Created the `AboutUs` page on product website
  * Updated the `Index` to reflect the latest release

* **Enhancements to existing features**:
  * Updated the styling of GUI (Pull requests [\#73](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/73))
  * Wrote additional tests for existing features to increase coverage (Pull requests [\#73](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/73), [\#53](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/53))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `add` `edit` `delete` and `editappt` [\#165](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/165), [\#235](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/235)
    * Added screenshots for all the features [\#120](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/120)
    * Made improvements to existing documentation based on peer reviews [\#140](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/140)
  * Developer Guide:
    * Updated details of the diagrams `ModelClassDiagram` and `BetterModelClassDiagram` [\#96](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/96)
    * Added implementation details and new use cases for the features `add` [\#96](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/96)
    * Made improvements to existing documentation based on peer reviews [\#140](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/140)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#234](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/234) [\#67](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/67) [\#61](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/61)
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
