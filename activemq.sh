#!/bin/bash

kubectl run activemq --image webcenter/activemq:latest --port 61616 --expose=true
