"use strict";
const assert=require("node:assert/strict");const {analyze,MIN_LENGTH}=require("../docs/policy.js");let n=0;
function check(expected,password,username,label){assert.equal(analyze(password,username).accepted,expected,label);n++;}
check(false,null,null,"null");check(false,"","","empty");check(false,"abc","","short");check(false,"abcdefghijklmn","","14");check(true,"abcdefghijklmno","","15");check(true,"abcdefghijklmnop","","16");check(true,"This is a longer passphrase with spaces","","passphrase");check(true,"abcdefghijklmnopqrst","","no symbol needed");check(true,"a long sentence with !# in it","","punctuation");
check(true,"🍉".repeat(15),"","emoji");check(true,"é".repeat(15),"","accented");check(true,"x".repeat(64),"","64");check(true,"x".repeat(1024),"","1024");check(false,"x".repeat(1025),"","1025");check(false,"PASSWORDPASSWORD","","blocked mixed case");check(false,"123456789012345","","numeric blocklist");check(false,"This is bad\nokay","","control");check(false,"correcthorsebatterystaple","","famous sample");check(true,"my unusual long password phrase",null,"null username");check(true,"my unusual long password phrase","","empty username");check(true,"my unusual long password phrase","A","one letter username");check(true,"a different 15 char phrase!","Adam","name absent");check(true,"a long sentence with password inside","","whole match only");
const a=analyze("my name is adam and I remember this","Adam");assert.ok(a.accepted&&a.advice.length);n++;
const b=analyze("PASSWORDPASSWORD","");assert.ok(b.errors.length&&!b.errors.join(" ").includes("PASSWORDPASSWORD"));n++;
check(false,"a".repeat(15),"","15 repeated blocked");assert.equal(MIN_LENGTH,15);n++;
assert.equal(analyze("x".repeat(15),"").checks.printable,true);n++;
assert.equal(analyze("abc","").checks.length,false);n++;
console.log("Browser policy tests: "+n+"/"+n+" passed");
