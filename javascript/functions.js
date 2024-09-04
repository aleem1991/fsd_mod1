function oldVersion()
{
    console.log("hi i am old funtion"    )
}
oldVersion()
newversion= () =>{
    console.log("hi i am new funtion")
}
newversion()
//old funtion with return
function oldVersion2(no1){
    return no1+10;
}
console.log(oldVersion2(30))


let newversion2 = (no1, no2) => {
    return no1 + no2;
};

console.log(newversion2(10, 20)); // This will log 30

newversion3=no1=>no1+10
console.log(newversion3(10))