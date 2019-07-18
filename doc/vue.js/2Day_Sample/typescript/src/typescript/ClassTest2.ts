
export interface AbstractFactory {
    createJavascript(): AbstractJavascript;
    createTypescript(): AbstractTypescript;
}

export class VueFactory implements AbstractFactory {
    public createJavascript(): AbstractJavascript {
        return new VueJavascript();
    }

    public createTypescript(): AbstractTypescript {
        return new VueTypescript();
    }
}

export class ReactFactory implements AbstractFactory {
    public createJavascript(): AbstractJavascript {
        return new ReactJavascript();
    }

    public createTypescript(): AbstractTypescript {
        return new ReactTypescript();
    }
}

export interface AbstractJavascript {
    frmeworksName(): string;
}

export class VueJavascript implements AbstractJavascript {
    frmeworksName(): string {
        return 'Vuejs';
    }

}

export class ReactJavascript implements AbstractJavascript {
    frmeworksName(): string {
        return 'Reactjs';
    }

}


export interface AbstractTypescript {
    frmeworksName(): string;
}


export class VueTypescript implements AbstractTypescript {
    frmeworksName(): string {
        return 'Vuets';
    }
}

export class ReactTypescript implements AbstractTypescript {
    frmeworksName(): string {
        return 'Reactts';
    }
}

