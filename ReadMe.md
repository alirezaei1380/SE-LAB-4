# Design Patterns Examination

## Report

### Step1: Select how to use patterns

In this examination we have two main topics:
1. `State`
2. `Transition`

We can consider `state` as two classes called `Delivered` and `Transit` which both have only a single function for printing the status,
so they are two classes with same function schema. But on the other hand, we have `Transition` which has two different approaches: `Standard` and `Express`.
In these two approaches we have two different strategies for transit a shipment, so they are not different types of a single entity and they have their own way of overall behaviour.
So, according to the previous statements we can use `State` pattern for `State` and `Strategy` pattern for `transit`.


### Step2: Implement tests for 