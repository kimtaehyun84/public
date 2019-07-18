
export interface IUnit {
    eat(): string;
    work(): string;
    availity(): string;
    description(): string;
    comment(): string;
}

export abstract class Human implements IUnit {
    public eat(): string {
        return "3";
    }
    public abstract work(): string;
    public abstract availity(): string;
    public description(): string {
        return "사람과의 영장류 동물";
    }

    public comment(): string {
        return `${this.constructor.name} 은 밥을 하루에 ${this.eat()} 번 
먹는 ${this.description()} 이다`;
    }
}

export abstract class Monster implements IUnit {
    public eat(): string {
        return "5";
    }
    public work(): string {
        return "대충";
    }
    public availity(): string {
        return "바보같음";
    }
    public description(): string {
        return "괴물";
    }

    public comment(): string {
        return `${this.constructor.name} 은 밥을 하루에 ${this.eat()} 번 
먹는 ${this.description()} 이다`;
    }

}

export class WieKuKu extends Human {
    public work(): string {
        return "열심히";
    }

    public availity(): string {
        return "갑질";
    }

    // public description(): string {
    //     return "요즘 갑자기 열심히 일하는 존재";
    // }
}

export class SeniorCitizens extends Human {
    public work(): string {
        return "열심히";
    }

    public availity(): string {
        return "내가 부족한게 많아요~~~";
    }

    // public description(): string {
    //     return "놀고 먹고 싶어 하시는 어르신";
    // }
}

export class JWMaster extends Human {
    public work(): string {
        return "너무 열심히";
    }

    public availity(): string {
        return "빵순이";
    }

    // public description(): string {
    //     return "따르릉~~ 따르릉~~ 비켜나세요~~";
    // }
}

export class Moon extends Monster {
    public eat(): string {
        return "아!!! 배고프다구요!!!";
    }

    public work(): string {
        return "놈팽이";
    }

    public availity(): string {
        return "useless";
    }


    public comment(): string {
        return `${this.constructor.name} 은 항상 [${this.eat()}] 를
    입에달고 사는 ${this.description()} 이다`;
    }
}

export class ChaCha extends Monster {
    public eat(): string {
        return "3";
    }

    public work(): string {
        return "열심";
    }

    public availity(): string {
        return "leading";
    }

    public description(): string {
        return "딸 바보";
    }

    // public comment(): string {
    //     return `${this.constructor.name} 는  ${this.description()} 이다`;
    // }
}


export class ClassTest {

}
