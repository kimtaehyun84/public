
export interface IUnit {
    name: string;
    nick: string;
    power: number;
    int: number;
    agility: number;

    attack(): string;
    move(): string;
    skill(): string;

}

export abstract class BaseUnit implements IUnit {
    private _name!: string;
    private _nick!: string;
    private _power!: number;
    private _int!: number;
    private _agility!: number;

    get name(): string { return this._name; }
    set name(value) { this._name = value; }

    get nick(): string { return this._nick; }
    set nick(value) { this._nick = value; }

    get power(): number { return this._power; }
    set power(value) { this._power = value; }

    get int(): number { return this._int; }
    set int(value) { this._int = value; }

    get agility(): number { return this._agility; }
    set agility(value) { this._agility = value; }

    constructor() {
        this._name = this.constructor.name;
        this._nick = `nick`;
        this._power = 34;
        this._int = 33;
        this._agility = 33;
    }
    public abstract attack(): string;
    public abstract move(): string;
    public abstract skill(): string;


}

export abstract class Human extends BaseUnit {
    constructor() {
        super();
        this.power = 25;
        this.int = 45;
        this.agility = 30;
    }
    attack(): string {
        return "도구를 사용 하여 공격";
    }
    move(): string {
        return "두발로 뚜벅 뚜벅";
    }
    skill(): string {
        return "휏불 공격";
    }
    description() : string {
       return `지금 ${super.name}가 ${this.move()} 움직여 ${this.attack()}`;
    }

}

export abstract class Monster extends BaseUnit {
    constructor() {
        super();
        this.power = 45;
        this.int = 25;
        this.agility = 30;
    }

    attack(): string {
        return "이빨과 몸으로 공격";
    }
    move(): string {
        return "네발로 성큼 성큼";
    }
    skill(): string {
        return "박치기";
    }

}

export class KuKu extends Human {
    constructor() {
        super();
        this.power = 40;
        this.int = 45;
        this.agility = 15;
        this.nick = '갑';
    }

    skill(): string {
        return "사과 찢기";
    }

}

export class SeniorCitizens extends Human {
    constructor() {
        super();
        this.power = 15;
        this.int = 70;
        this.agility = 15;
        this.nick = '어르신';
    }

    skill(): string {
        return "내가 부족한게 많아....";
    }

}

export class ChaCha extends Human {
    constructor() {
        super();
        this.nick = '차차';
    }
    skill(): string {
        return "또 나야?";
    }

}

export class JWMaster extends Human {

    constructor() {
        super();
        this.nick = '빵순이';
    }

    move(): string {
        return "자전거로";
    }

    skill(): string {
        return "따르릉~ 따르릉~";
    }

}


export class Moon extends Monster {

    constructor() {
        super();
        this.power = 65;
        this.int = 5;
        this.nick = '루돌프';
    }

    attack(): string {
        let attack = `${super.attack()} 후 침으로 퉷~`;
        return attack;
    }

    skill(): string {
        return "아~~~배고프다구요~~";
    }
    description() : string {
        return `지금 ${this.nick}가 ${this.move()} 움직여 ${this.attack()}`;
    }




}
