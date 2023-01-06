/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MongoDB
 Source Server Version : 60003
 Source Host           : localhost:27017
 Source Schema         : test

 Target Server Type    : MongoDB
 Target Server Version : 60003
 File Encoding         : 65001

 Date: 06/01/2023 16:50:53
*/


// ----------------------------
// Collection structure for demo_collection
// ----------------------------
db.getCollection("demo_collection").drop();
db.createCollection("demo_collection");

// ----------------------------
// Documents of demo_collection
// ----------------------------
db.getCollection("demo_collection").insert([ {
    _id: ObjectId("63b7b78deeaed6e5bae9131a"),
    author: "Maxsu",
    text: "My first blog post!",
    tags: [
        "mongodb",
        "python",
        "pymongo"
    ],
    date: ISODate("2023-01-06T05:54:21.548Z")
} ]);
db.getCollection("demo_collection").insert([ {
    _id: ObjectId("63b7b8ccf9f31852e52ae98f"),
    id: "2",
    name: "empty.png",
    uid: "19",
    matha: "x+1",
    mini: -10,
    maxi: 10,
    qal: 0.001,
    wei: 10,
    len: 10
} ]);

// ----------------------------
// Collection structure for imginf
// ----------------------------
db.getCollection("imginf").drop();
db.createCollection("imginf");

// ----------------------------
// Documents of imginf
// ----------------------------
db.getCollection("imginf").insert([ {
    _id: "2",
    name: "empty.png",
    uid: "0",
    matha: "x+1",
    mini: -10,
    maxi: 10,
    qal: 0.001,
    wei: 10,
    len: 10
} ]);
db.getCollection("imginf").insert([ {
    _id: NumberInt("3"),
    name: "empty.png",
    uid: "0",
    matha: "x+1",
    mini: -10,
    maxi: 10,
    qal: 0.001,
    wei: 10,
    lent: 0,
    _class: "com.example.finalwork4.domain.MongoPyDetail",
    len: 10
} ]);
db.getCollection("imginf").insert([ {
    _id: NumberInt("4"),
    name: "empty.png",
    uid: "0",
    matha: "x+1",
    mini: -10,
    maxi: 10,
    qal: 0.001,
    wei: 10,
    lent: 0,
    _class: "com.example.finalwork4.domain.MongoPyDetail",
    len: 10
} ]);
db.getCollection("imginf").insert([ {
    _id: NumberInt("5"),
    name: "empty.png",
    uid: "0",
    matha: "x+1",
    mini: -10,
    maxi: 10,
    qal: 0.001,
    wei: 10,
    lent: 0,
    _class: "com.example.finalwork4.domain.MongoPyDetail",
    len: 10
} ]);
db.getCollection("imginf").insert([ {
    _id: NumberInt("8"),
    name: "1.png",
    uid: "19",
    matha: "(cos(x))",
    mini: NumberInt("-10"),
    maxi: NumberInt("10"),
    qal: 0.001,
    wei: 10,
    lent: 0,
    _class: "com.example.finalwork4.domain.MongoPyDetail",
    len: 10
} ]);
db.getCollection("imginf").insert([ {
    _id: NumberInt("9"),
    name: "name",
    uid: "uid",
    matha: "matha",
    mini: 0,
    maxi: 0,
    qal: 0,
    wei: 0,
    lent: 0,
    _class: "com.example.finalwork4.domain.MongoPyDetail"
} ]);

// ----------------------------
// Collection structure for posts
// ----------------------------
db.getCollection("posts").drop();
db.createCollection("posts");

// ----------------------------
// Documents of posts
// ----------------------------
db.getCollection("posts").insert([ {
    _id: ObjectId("63b7b6bed700d4af3c539c34"),
    author: "Maxsu",
    text: "My first blog post!",
    tags: [
        "mongodb",
        "python",
        "pymongo"
    ],
    date: ISODate("2023-01-06T05:50:54.983Z")
} ]);
db.getCollection("posts").insert([ {
    _id: ObjectId("63b7b748e8a471bb5dbff72e"),
    author: "Maxsu",
    text: "My first blog post!",
    tags: [
        "mongodb",
        "python",
        "pymongo"
    ],
    date: ISODate("2023-01-06T05:53:12.067Z")
} ]);

// ----------------------------
// Collection structure for test
// ----------------------------
db.getCollection("test").drop();
db.createCollection("test");

// ----------------------------
// Documents of test
// ----------------------------

// ----------------------------
// Collection structure for test1
// ----------------------------
db.getCollection("test1").drop();
db.createCollection("test1");

// ----------------------------
// Documents of test1
// ----------------------------
