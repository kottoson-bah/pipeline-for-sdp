------
Gradle
------

Provides pipeline steps for the SDP pipelines that either trigger or leverage Gradle


Steps Provided
==============

- unit_test()


Example Configuration Snippet
=============================

.. code:: groovy

   libraries{
     gradle {
       image {
         name = "gradle:4.10.2-jdk8"
       }
     }
   }

Configurations
==============

.. csv-table:: Gradle Configuration Options
   :header: "Field", "Description", "Default Value", "Required"

   "image.name", "The full name of the image being used in gradle build/tests", "none", "true"
   "image.cred", "The Jenkins username/password credential to log into the images repository", "none", "false"
