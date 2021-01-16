import { IEnvironment } from "../../environments/ienvironment";
import { environment } from "../../environments/environment";
import { Injectable } from "@angular/core"

@Injectable({
    providedIn: "root"
})
export class EnvironmentService implements IEnvironment {
    get production()
    {
        return environment.production;
    }
    set production(value: boolean)
    {
        environment.production = value;

    }
    get apiHost()
    {
        return environment.apiHost;
    }
    get apiUrlVote()
    {
        return environment.apiUrlVote;
    }

    constructor(){
        
    }
}