
import { Answer } from './answer';
export interface QuestionToDispolay {
  answerTime: Date | undefined;
  displayTime: Date | undefined;
  selectedAnswer: Answer | undefined;
  id: string;
  name?: string;
  levelId?: number;
  categoryId?: number;
  mark?: number;
  expectedTime?: number;
  correctAnswerIds?: string[];
  createdBy?: string;
  createdAt?: string;
  answers: Answer[];
}
