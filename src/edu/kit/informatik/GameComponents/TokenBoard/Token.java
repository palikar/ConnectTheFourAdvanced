/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.kit.informatik.GameComponents.TokenBoard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stanislav
 * @version 0.0.1
 */
public class Token {

    public enum Size {
        /**
         * Quality for big token
         */
        big("big"),
        /**
         * Quality for small token
         */
        small("small");

        private final String value;

        private Size(String size) {
            this.value = size;
        }

        /**
         * Basic getter
         *
         * @return The value of the quality
         */
        public String getValue() {
            return value;
        }
    }

    public enum Color {
        /**
         * Quality for white token
         */
        white("white"),
        /**
         * Quality for black token
         */
        black("black");
        private final String value;

        private Color(String color) {
            this.value = color;
        }

        /**
         * Basic getter
         *
         * @return The value of the quality
         */
        public String getValue() {
            return value;
        }
    }

    public enum Shape {
        /**
         * Quality for rectangular big token
         */
        rectangle("rectangle"),
        /**
         * Quality for cylindical token
         */
        cylinder("cylinder");
        private final String value;

        private Shape(String shape) {
            this.value = shape;
        }

        /**
         * Basic getter
         *
         * @return The value of the quality
         */
        public String getValue() {
            return value;
        }
    }

    public enum Form {
        /**
         * Quality for dense(massive) token
         */
        dense("dense"),
        /**
         * Quality for hollow token
         */
        hollow("hollow");
        private final String value;

        private Form(String form) {
            this.value = form;
        }

        /**
         * Basic getter
         *
         * @return The value of the quality
         */
        public String getValue() {
            return value;
        }
    }

    private final Size size;

    private final Color color;

    private final Shape shape;

    private final Form form;
    private final int id;

    /**
     * Constructs a token with the given qualities
     *
     * @param size The size of the token
     * @param color The color of the token
     * @param shape The shape of the token
     * @param form The form of the token
     * @param id Identification number
     */
    public Token(Size size, Color color, Shape shape, Form form, int id) {
        this.size = size;
        this.color = color;
        this.shape = shape;
        this.form = form;
        this.id = id;
    }

    /**
     *
     * @return All of the qualities values as List of strings
     */
    public List<String> getAllQualities() {
        List<String> list = new ArrayList<>();
        list.add(this.color.getValue());
        list.add(this.shape.getValue());
        list.add(this.form.getValue());
        list.add(this.shape.getValue());
        return list;
    }

    /**
     * Basic getter for the size
     *
     * @return The size quality
     */
    public Size getSize() {
        return size;
    }

    /**
     * Basic getter for the color
     *
     * @return The color quality
     */
    public Color getColor() {
        return color;
    }

    /**
     * Basic getter for the shape
     *
     * @return The shape quality
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Basic getter for the form
     *
     * @return The form quality
     */
    public Form getForm() {
        return form;
    }

    /**
     * Basic getter for the number
     *
     * @return The identification number
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        return ((Token) obj).getId() == this.getId();
    }

}
