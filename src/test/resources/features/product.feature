Feature: product data table practice

  @listOfMap
  Scenario: verify each product price
    #practice List<Map<String,String>>
    Given User is on the HomePage
    Then User should be able to see expected prices in the following products
      | Category | Product           | expectedPrice |
      | Phones   | Samsung galaxy s6 | 360           |
      | Phones   | Nokia lumia 1520  | 820           |
      | Phones   | Nexus 6           | 650           |
      | Laptops  | Sony vaio i5      | 790           |
      | Laptops  | Sony vaio i7      | 790           |
      | Laptops  | MacBook air       | 700           |
      | Monitors | Apple monitor 24  | 400           |
      | Monitors | ASUS Full HD      | 230           |

    #List Element 1

  @listOfList

  #practice list <List<String>>
  Scenario: verify each product price ListOfList
    Given User is on the HomePage
    Then User should be able to see expected prices in the following products with listOfList
      | Phones   | Samsung galaxy s6 | 360 |
      | Phones   | Nokia lumia 1520  | 820 |
      | Phones   | Nexus 6           | 650 |
      | Laptops  | Sony vaio i5      | 790 |
      | Laptops  | Sony vaio i7      | 790 |
      | Laptops  | MacBook air       | 700 |
      | Monitors | Apple monitor 24  | 400 |
      | Monitors | ASUS Full HD      | 230 |

  @mapList
  Scenario: verify students names on discord
    Then user should be able to see the following names in their groups
      | Group 1 | Nadir | Feh  | Shack |
      | Group 2 | Julia | Rome | Fakir |