---
layout: page
title: Eugene's Project Portfolio Page
---

## Project: CliniCal

CliniCal is a desktop app that allows doctors to manage patient records and schedule upcoming appointments.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: Allows the user to undo all previous commands that modify the patient database, one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. 
  The implementation was challenging as it required changes to existing commands and continuous updates for new commands added at each iteration. This included new commands such as adding, editing, deleting of visits and appointments added in `v1.3`
  * Credits:
    1. Existing AB3 codebase: https://github.com/se-edu/addressbook-level3
    
* **New Feature**: Added the command to edit an existing visitation log.
  * What it does: Allows the user to modify or remove the existing visitation log seen from the profile window.
  * Justification: This feature allows the user to amend visit details when the user makes a mistake or wants to make amendments to an existing visit summary.
  * Highlights: Also implemented the functionality of a new popup window to modify visit details, as stated below.
  * Credits: TODO

* **New Feature**: Implemented a pop-up window to add and edit a visitation log.
  * What it does: Allows the user to add or modify visit details such as diagnosis, prescription and comments in a user-friendly interface.
  * Justification: This feature improves the product significantly as there is a new pop-up window for the user to manage visit details other than the main GUI.
  * Highlights: Created UI mockups and implemented this pop-up window for use in add visit and edit visit command.
  * Credits: TODO

 * **New Feature**: Added the command to delete an existing visitation log.
   * What it does: Allows the user to remove delete a visitation log based on its index in the profile window.
   * Justification: This feature allows the user to organize the visitation logs and remove visits that are not required anymore.
   * Highlights: Also enabled the undo function for the visit commands. 
   * Credits: TODO


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=eugene3231)

* **Project management**:
  * Managed releases `v1.3` on GitHub

* **Enhancements to existing features**:
  * Improved the GUI for the main application window. [\#104](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/104)
  * Updated the GUI for the Profile Window. [\#149](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/149)
  
 * **Enhancements to new features**:
  * Added a GUI for the Visitation Log Window. [\#149](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/149) 
  * Wrote additional tests for `undo`, `redo`, `AddVisitParser`, `ProfileCommandParser`, 
  `DeleteVisitCommand`, `DeleteVisitCommandParser`, `EditVisitCommand`, `EditVisitCommandParser`classes. [\#160](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/160), [\#166](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/166)

* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo`, `redo`, `editvisit` and `deletevisit` [\#160](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/160)
    * Did overall formatting tweaks for the document by reorganizing content into respective categories. [\#118](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/118), [\#119](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/119)
    * Added more information to introduce user about the GUI. [\#240](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/240)
    * Did cosmetic tweaks to overall table of contents and existing documentation of feature: `Retrieving past commands using arrow keys`. [\#112](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/112)
    * Added formatting guidelines for the user to follow. [\#173](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/173)
  * Developer Guide:
    * Added implementation details of the `undo`, `redo` feature. [\#55](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/55)
    * Improved labelling of headings and subheadings for better readability. [\#75](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/75)
    * Added labelling of all figures used in the developer guide. [\#101](https://github.com/AY2021S1-CS2103T-W11-4/tp/pull/101)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]() (TODO)
  * Contributed to forum discussions: [\#159](https://github.com/nus-cs2103-AY2021S1/forum/issues/159)
  * Reported bugs and suggestions for other teams in the class (during Practical Exam - Dry Run): [Issues Link](https://github.com/eugene3231/ped/issues)

* **Tools**:
  * Integrated a third party library (JFoenix) to the project ([\#42]()) (TODO)


