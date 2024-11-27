# Lab 2 Solutions
## Question 1
### 1.1 Detect verbs from Problem Description
A project, before final release, **is required** to **have** a specified feature set.
Associated with a project are multiple releases. A release is a functional piece of
the project **being developed** that **includes** a subset of the feature set for the
project and which is **to be delivered** on a specified date (the feature set and
release date **are determined** by the Project Manager). When the last release is
**delivered**, the project **is considered completed**.
**Associated** with each feature for a project is a developer who is responsible for
**developing** this feature for inclusion in the project. A developer has an id and
**provides**, for each feature he **is responsible for**, the **estimated** time remaining to
**complete** work on that feature.
### 1.2 Build association matrix

|  | Project | Developer | Release | Feature |
| --- | --- | --- | --- | --- |
| Project | - | - | includes | includes |
| Developer | - | - | - | responsible for developing |
| Release | belongs to | - | - | includes |
| Feature | belongs to | developed by | part of | - |

### 1.3 Diagram associations
![Screenshot 2024-11-26 at 17.47.16.png](https://github.com/user-attachments/assets/852ddfef-8ca7-4195-ae4f-705a8c119dc7)

### 1.4 Final Associations and Dependencies
![Screenshot 2024-11-26 at 18.09.15.png](https://github.com/user-attachments/assets/64492ef8-6a53-4e71-b711-f3c6b81c7f6f)

## Question 2
