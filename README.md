# Nhplugin (Test Plugin)

## Goal
The goal of this plugin was to allow for the use of the Nashorn engine in minecraft 1.17+ with the inclusion of the standalone engine. Additionally the goal of the plugin
was to allow for the quick prototyping of commands in Javascript which could then be fully developed in Java or as a part of another plugin. 

## Config
The only config option is fileName, which is the entry file for JS. Using load() in the js files (supported by nashorn) multiple scripts can be used. 

## Dependencies
Currently the plugin depends on Vault

## Future Support
I do not have any fixed plans on supporting/improving this plugin. It is merely a project that I work on when I have free time.

## Examples
The single-script example does not fully work as I changed the way in which events are called from Java. However the premise is the same and events can be easily checked by getting the event name.
