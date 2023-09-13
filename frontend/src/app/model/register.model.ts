export class Register{
    constructor(
        public email: string,
        public username: string,
        public fullName: string,
        public password: string,
        public role: string[],
        
        ){}
}