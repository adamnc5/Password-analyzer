(function(root,factory){"use strict";const p=factory();if(typeof module==="object"&&module.exports)module.exports=p;else root.PasswordPolicy=p;})(typeof globalThis!=="undefined"?globalThis:this,function(){"use strict";
const MIN_LENGTH=15,MAX_DEMO_LENGTH=1024;
const BLOCKLIST=["password","password1","password123","password123@","123456","12345678","123456789","1234567890","123456789012345","1234567890123456","12345678901234567890","admin","admin123","admin123@","administrator","qwerty","qwerty123","qwertyuiop","qwertyuiop123@","letmein","welcome","welcome123","iloveyou","iloveyou2@","monkey","dragon","football","sunshine","princess","aaaaaaaaaaaaaaa","passwordpassword","correcthorsebatterystaple","thisismypassword","supersecretpassword","mysecretpassword"];
function analyze(password,username){
 const errors=[],advice=[];
 if(typeof password!=="string"||password.length===0)return {accepted:false,length:0,errors:["Enter a password to check."],advice,checks:{length:false,blocklist:null,printable:null}};
 const length=Array.from(password).length;
 if(length<MIN_LENGTH)errors.push("Use at least "+MIN_LENGTH+" characters (currently "+length+").");
 if(length>MAX_DEMO_LENGTH)errors.push("This demo accepts at most "+MAX_DEMO_LENGTH+" characters per check.");
 const printable=!/[\u0000-\u001f\u007f-\u009f]/.test(password);
 if(!printable)errors.push("Remove non-printing control characters from the password.");
 const lower=password.toLowerCase(),blocklist=!BLOCKLIST.some(x=>lower===x.toLowerCase());
 if(!blocklist)errors.push("This password matches an entry in the sample common-password blocklist.");
 if(typeof username==="string"){const name=username.trim().toLowerCase();if(name.length>=3&&lower.includes(name))advice.push("Avoid including your name or username in a password.");}
 if(length>=MIN_LENGTH&&length<20)advice.push("A longer, unique passphrase may be easier to remember and harder to guess.");
 return {accepted:errors.length===0,length,errors,advice,checks:{length:length>=MIN_LENGTH,blocklist,printable:printable&&length<=MAX_DEMO_LENGTH}};
}
return {analyze,MIN_LENGTH,MAX_DEMO_LENGTH};
});