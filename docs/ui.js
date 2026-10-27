(function(){"use strict";
const policy=window.PasswordPolicy;const $=id=>document.getElementById(id);
const pwd=$("password"),user=$("username");
function setRule(id,state){const el=$(id);el.classList.remove("good","bad");el.querySelector(".check-icon").textContent=state===null?"–":state?"✓":"×";if(state!==null)el.classList.add(state?"good":"bad");}
function reset(){ $("result").className="hero-result";$("symbol").textContent="◇";$("verdict").textContent="Ready when you are";$("message").textContent="Enter a made-up password or choose an example to see the results.";$("status").textContent="READY";$("count").textContent="0 of 3 checked";["length-check","common-check","control-check"].forEach(id=>setRule(id,null));$("advice").hidden=true;$("advice").textContent="";}
function counter(){const len=Array.from(pwd.value).length;$("length").textContent=len+" / 15 characters minimum";if(!pwd.value)reset();}
function analyze(){const r=policy.analyze(pwd.value,user.value);$("result").className="hero-result "+(r.accepted?"pass":"fail");$("symbol").textContent=r.accepted?"✓":"×";$("verdict").textContent=r.accepted?"Policy checks passed":"Not accepted by demo policy";$("message").textContent=r.accepted?"This example passed our basic checks. It may still be unsafe for a real account.":r.errors.join(" ");$("status").textContent=r.accepted?"PASS":"REVIEW";setRule("length-check",r.checks.length);setRule("common-check",r.checks.blocklist);setRule("control-check",r.checks.printable);$("count").textContent=Object.values(r.checks).filter(Boolean).length+" of 3 passed";$("advice").hidden=!r.advice.length;$("advice").textContent=r.advice.join(" ");}
$("checker").addEventListener("submit",e=>{e.preventDefault();analyze();});
pwd.addEventListener("input",counter);
$("clear").addEventListener("click",()=>{pwd.value="";user.value="";counter();pwd.focus();});
$("reveal").addEventListener("click",()=>{const show=pwd.type==="password";pwd.type=show?"text":"password";$("reveal").textContent=show?"Hide":"Show";$("reveal").setAttribute("aria-pressed",String(show));});
const samples={short:"hello123",common:"PASSWORDPASSWORD",phrase:"an evening walk in Prague"};
document.querySelectorAll("[data-example]").forEach(el=>el.addEventListener("click",()=>{pwd.value=samples[el.dataset.example];user.value="";counter();analyze();}));
reset();
})();