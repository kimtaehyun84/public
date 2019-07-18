
const user: { name: string; weight: number; } = { name: '문X롱', weight: 100 };
const userWithUnknownWeight: { name: string; weight?: number; } = { name: '백나피' };
const userWithReadOnlyWeight: { name: string; readonly weight: number; } = { name: '문먹자', weight: 100 };
type User = { name: string; weight: number; };
type UserWithUnknownWeight = { name: string; weight?: number; };
function sum(a: number, b: number): number {
    return a + b;
}

class LegacyStack {
    private data: any[] = [];

    contructor() { }

    push(item: any): void {
        this.data.push(item);
    }

    pop(): any {
        return this.data.pop();
    }
}

class Stack<T> {
    private data: T[] = [];

    constructor() { }

    push(item: T): void {
        this.data.push(item);
    }

    pop(): T | undefined {
        return this.data.pop();
    }
}

enum Rank {
    Iron = 0,
    Bronze,
    Silver,
    Gold,
    Platinum,
    Diamond,
    VIP
}

enum Direction {
    East = 'East',
    West = 'West',
    South = 'South',
    North = 'North'
}

export class BaseTest {
    _boolean: boolean = true;
    _number: number = 0;
    _string: string = '';
    _null: null = null;
    _undifined: undefined = undefined;
    _any: any = '';

    voidTest(): void {
        //return 'a';
        //return null;
        //return undefined;
        return;
    }

    neverTest(): never {
        //return 'a';
        //return null;
        //return undefined;
        throw new Error(`not implementd exception`);
    }

    arrayTest() {
        let listArray: number[] = [];
        listArray[0] = 1;
        listArray.push(2);
        console.log(listArray);
        return listArray;
    }

    tupleTest() {
        let [a, b] = ['문찌롱', 100];
        let nameAndWeight: [string, number] = [a, b];
        let aa = nameAndWeight;
        console.log(aa);
        return aa;
    }

    objectTest() {
        let localUser = user;
        let unknownWeightUser: UserWithUnknownWeight = userWithUnknownWeight;
        let readonlyWeightUser = userWithReadOnlyWeight;
        //readonlyWeightUser.weight = 100;
        //console.log(`name = ${unknownWeightUser.name}  weight = ${unknownWeightUser.weight}`);

        console.log(`user ${JSON.stringify(user)}`);
        console.log(`userWithUnknownWeight ${JSON.stringify(unknownWeightUser)}`);
        console.log(`userWithReadOnlyWeight ${JSON.stringify(readonlyWeightUser)}`);
        //console.log(`user ${user}`);

        return [`user ${JSON.stringify(user)}`
            , `userWithUnknownWeight ${JSON.stringify(unknownWeightUser)}`
            , `userWithReadOnlyWeight ${JSON.stringify(readonlyWeightUser)}`];
    }

    functionTest() {
        let sumValue = `sum(1,2) => ${sum(1, 2)}`;
        console.log(sumValue);
        return sumValue;
    }

    genericTest() {

        this.genericStackTest();
        this.genericStackFailedTest();

        this.legacyStackTest();
        this.legacyStackFailedTest();

    }

    genericStackTest() {
        let genericStack = new Stack<number>();
        genericStack.push(10000);
        let data = genericStack.pop();

        let localeString = data!.toFixed(10);

    }

    genericStackFailedTest() {
        let genericStack = new Stack<number>();
        genericStack.push(10000);

        let a = '10000';
        genericStack.push(a); // compiletime error


    }

    legacyStackTest() {
        let legacyStack = new LegacyStack();
        legacyStack.push(10000);
        let data = legacyStack.pop();
        console.log(data!.toFixed(10));
    }

    legacyStackFailedTest() {

        let legacyStack = new LegacyStack();
        legacyStack.push(10000);

        let a = '10000';
        legacyStack.push(a);
        let data = legacyStack.pop();
        console.log(data!.toFixed(10)); // runtime error


    }

    enumTest() {
        let moonBankRank = Rank.Iron;
        let wiekukuBankRank = Rank.VIP;
        let direction = Direction.East;

        let myDirection = "EAST";

        if (myDirection == "East") {
            //bla bla
        } else {
            //bla bla
        }

        //


        if (direction == Direction.East) {
            //bla bla
        } else {
            //bla bla
        }



    }



}