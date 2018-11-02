import {Authority} from './authority.enum';

export class User {
  id: string;
  email: string;
  authority: Authority;
  isActive: boolean;
  token: string;
  refreshToken: string;
  firstName: string;
  lastName: string;
}
