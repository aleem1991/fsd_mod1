//spread operator
const set1=[10,20,30,40,50]
const set2=[...set1,60,70,80,90,100]
console.log(set2)

const student = {
    sname: "raj", // Correct use of key-value pair with a colon
    sage: 20
};

const sectionD = {
    ...student,   // Spread the properties of the student object into sectionD
    no_student: 71
};

console.log(sectionD);
