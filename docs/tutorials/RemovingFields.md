---
layout: page
title: "Tutorial: Removing Fields"
---

> Perfection is achieved, not when there is nothing more to add, but when there is nothing left to take away.
>
> —  Antoine de Saint-Exupery

When working on AddressBook, you will most likely find that some features and fields that are no longer necessary. In scenarios like this, you can consider refactoring the existing `Person` model to suit your use case.

In this tutorial, we’ll do exactly just that and remove the `address` field from `Person`.

* Table of Contents
{:toc}

**`PersonCard.java`**

``` java
...
@FXML
private Label address;
...
```

**`PersonCard.fxml`**

``` xml
...
<Label fx:id="phone" styleClass="cell_small_label" text="\$phone" />
<Label fx:id="address" styleClass="cell_small_label" text="\$address" />
<Label fx:id="email" styleClass="cell_small_label" text="\$email" />
...
```

After removing the `Label`, we can proceed to formally test our code. If everything went well, you should have most of your tests pass. Fix any remaining errors until the tests all pass.

## Tidying up

At this point, your application is working as intended and all your tests are passing. What’s left to do is to clean up references to `Address` in test data and documentation.

In `src/test/data/`, data meant for testing purposes are stored. While keeping the `address` field in the json files does not cause the tests to fail, it is not good practice to let cruft from old features accumulate.

**`invalidPersonAddressBook.json`:**

```json
{
  "persons": [ {
    "name": "Person with invalid name field: Ha!ns Mu@ster",
    "phone": "9482424",
    "email": "hans@example.com",
  } ]
}
```

You can go through each individual `json` file and manually remove the `address` field.
