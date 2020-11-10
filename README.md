# Getting Started

This demonstrates how to use Spring JCache combined with JPA repository.

The managed entity is a CarModel which can be identifies by two type of functional keys:
* its technicalId (eg. id of the motor) (it's different from database id)
* its name and year

So the idea is to demonstrate how we can configure a Spring JpaRepository not to process multiple calls when
we invoke the findByTechnicalId() or the findByNameAndYear() methods.


### Still to do

The following features need to be done:

* cover the findAll() method

