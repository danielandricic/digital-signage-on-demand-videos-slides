#!/bin/bash

rm -rf gh-pages
mkdir gh-pages
cp -r ./asciidocs/* gh-pages/
asciidoctor -r asciidoctor-diagram -D ./gh-pages ./asciidocs/*.adoc