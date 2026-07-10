/*
 * Decompiled with CFR 0.152.
 */
package com.databricks.labs.morpheus.transform.rules.snowflake;

import com.databricks.labs.morpheus.errors.UnsupportedArguments;
import com.databricks.labs.morpheus.errors.UnsupportedArguments$;
import com.databricks.labs.morpheus.errors.UnsupportedDateTimePart;
import com.databricks.labs.morpheus.errors.UnsupportedDateTimePart$;
import com.databricks.labs.morpheus.errors.WrongNumberOfArguments;
import com.databricks.labs.morpheus.errors.WrongNumberOfArguments$;
import com.databricks.labs.morpheus.intermediate.Add;
import com.databricks.labs.morpheus.intermediate.Alias;
import com.databricks.labs.morpheus.intermediate.And;
import com.databricks.labs.morpheus.intermediate.ArrayAccess;
import com.databricks.labs.morpheus.intermediate.ArrayAggregate;
import com.databricks.labs.morpheus.intermediate.ArrayAggregate$;
import com.databricks.labs.morpheus.intermediate.ArrayAppend;
import com.databricks.labs.morpheus.intermediate.ArrayContains;
import com.databricks.labs.morpheus.intermediate.ArrayExcept;
import com.databricks.labs.morpheus.intermediate.ArrayFilter;
import com.databricks.labs.morpheus.intermediate.ArrayIntersect;
import com.databricks.labs.morpheus.intermediate.ArrayJoin;
import com.databricks.labs.morpheus.intermediate.ArrayJoin$;
import com.databricks.labs.morpheus.intermediate.ArraySort;
import com.databricks.labs.morpheus.intermediate.ArraySort$;
import com.databricks.labs.morpheus.intermediate.ArrayTransform;
import com.databricks.labs.morpheus.intermediate.ArrayType;
import com.databricks.labs.morpheus.intermediate.ArraysZip;
import com.databricks.labs.morpheus.intermediate.Base64;
import com.databricks.labs.morpheus.intermediate.BitOrAgg;
import com.databricks.labs.morpheus.intermediate.BoolAnd;
import com.databricks.labs.morpheus.intermediate.CallFunction;
import com.databricks.labs.morpheus.intermediate.CallMapper;
import com.databricks.labs.morpheus.intermediate.Case;
import com.databricks.labs.morpheus.intermediate.Cast;
import com.databricks.labs.morpheus.intermediate.Cast$;
import com.databricks.labs.morpheus.intermediate.Coalesce;
import com.databricks.labs.morpheus.intermediate.CollectList;
import com.databricks.labs.morpheus.intermediate.CollectList$;
import com.databricks.labs.morpheus.intermediate.Comma;
import com.databricks.labs.morpheus.intermediate.Concat;
import com.databricks.labs.morpheus.intermediate.CreateArray;
import com.databricks.labs.morpheus.intermediate.CreateArray$;
import com.databricks.labs.morpheus.intermediate.CurrentTimestamp;
import com.databricks.labs.morpheus.intermediate.DateAdd;
import com.databricks.labs.morpheus.intermediate.DateFormatClass;
import com.databricks.labs.morpheus.intermediate.DatePart;
import com.databricks.labs.morpheus.intermediate.DateType$;
import com.databricks.labs.morpheus.intermediate.DecimalType;
import com.databricks.labs.morpheus.intermediate.DecimalType$;
import com.databricks.labs.morpheus.intermediate.Divide;
import com.databricks.labs.morpheus.intermediate.Dot;
import com.databricks.labs.morpheus.intermediate.DoubleLiteral$;
import com.databricks.labs.morpheus.intermediate.DoubleType$;
import com.databricks.labs.morpheus.intermediate.Explode;
import com.databricks.labs.morpheus.intermediate.Expression;
import com.databricks.labs.morpheus.intermediate.ExpressionPrecedence;
import com.databricks.labs.morpheus.intermediate.Extract;
import com.databricks.labs.morpheus.intermediate.Filter;
import com.databricks.labs.morpheus.intermediate.First;
import com.databricks.labs.morpheus.intermediate.Flatten;
import com.databricks.labs.morpheus.intermediate.Floor;
import com.databricks.labs.morpheus.intermediate.Fn;
import com.databricks.labs.morpheus.intermediate.GetJsonObject;
import com.databricks.labs.morpheus.intermediate.GreaterThan;
import com.databricks.labs.morpheus.intermediate.GreaterThanOrEqual;
import com.databricks.labs.morpheus.intermediate.Hour;
import com.databricks.labs.morpheus.intermediate.Id;
import com.databricks.labs.morpheus.intermediate.Id$;
import com.databricks.labs.morpheus.intermediate.If;
import com.databricks.labs.morpheus.intermediate.IfNull;
import com.databricks.labs.morpheus.intermediate.In;
import com.databricks.labs.morpheus.intermediate.IntLiteral$;
import com.databricks.labs.morpheus.intermediate.IntegerType$;
import com.databricks.labs.morpheus.intermediate.IsNaN;
import com.databricks.labs.morpheus.intermediate.IsNotNull;
import com.databricks.labs.morpheus.intermediate.IsNull;
import com.databricks.labs.morpheus.intermediate.JsonObjectKeys;
import com.databricks.labs.morpheus.intermediate.LambdaFunction;
import com.databricks.labs.morpheus.intermediate.Last;
import com.databricks.labs.morpheus.intermediate.LastDay;
import com.databricks.labs.morpheus.intermediate.Length;
import com.databricks.labs.morpheus.intermediate.LessThan;
import com.databricks.labs.morpheus.intermediate.Levenshtein;
import com.databricks.labs.morpheus.intermediate.Literal;
import com.databricks.labs.morpheus.intermediate.Literal$;
import com.databricks.labs.morpheus.intermediate.LogicalPlan;
import com.databricks.labs.morpheus.intermediate.LongType$;
import com.databricks.labs.morpheus.intermediate.Lower;
import com.databricks.labs.morpheus.intermediate.MakeDate;
import com.databricks.labs.morpheus.intermediate.MakeTimestamp;
import com.databricks.labs.morpheus.intermediate.Minute;
import com.databricks.labs.morpheus.intermediate.Mod;
import com.databricks.labs.morpheus.intermediate.MonthsBetween;
import com.databricks.labs.morpheus.intermediate.Multiply;
import com.databricks.labs.morpheus.intermediate.NotEquals;
import com.databricks.labs.morpheus.intermediate.Or;
import com.databricks.labs.morpheus.intermediate.ParseJson;
import com.databricks.labs.morpheus.intermediate.ParseToDate;
import com.databricks.labs.morpheus.intermediate.ParseToTimestamp;
import com.databricks.labs.morpheus.intermediate.ParseToTimestamp$;
import com.databricks.labs.morpheus.intermediate.PosExplode;
import com.databricks.labs.morpheus.intermediate.Pow;
import com.databricks.labs.morpheus.intermediate.Project;
import com.databricks.labs.morpheus.intermediate.RLike;
import com.databricks.labs.morpheus.intermediate.RaiseError;
import com.databricks.labs.morpheus.intermediate.Rand;
import com.databricks.labs.morpheus.intermediate.RegExpExtract;
import com.databricks.labs.morpheus.intermediate.RegExpExtractAll;
import com.databricks.labs.morpheus.intermediate.RegExpExtractAll$;
import com.databricks.labs.morpheus.intermediate.RegExpReplace;
import com.databricks.labs.morpheus.intermediate.RegexpInstr;
import com.databricks.labs.morpheus.intermediate.ScalarSubquery;
import com.databricks.labs.morpheus.intermediate.Second;
import com.databricks.labs.morpheus.intermediate.Sha2;
import com.databricks.labs.morpheus.intermediate.Size;
import com.databricks.labs.morpheus.intermediate.Slice;
import com.databricks.labs.morpheus.intermediate.SortArray;
import com.databricks.labs.morpheus.intermediate.Star;
import com.databricks.labs.morpheus.intermediate.StartsWith;
import com.databricks.labs.morpheus.intermediate.StringLiteral$;
import com.databricks.labs.morpheus.intermediate.StringReplace;
import com.databricks.labs.morpheus.intermediate.StringSplit;
import com.databricks.labs.morpheus.intermediate.StringSplit$;
import com.databricks.labs.morpheus.intermediate.StringSplitPart;
import com.databricks.labs.morpheus.intermediate.StringType$;
import com.databricks.labs.morpheus.intermediate.StructExpr;
import com.databricks.labs.morpheus.intermediate.StructsToJson;
import com.databricks.labs.morpheus.intermediate.Substring;
import com.databricks.labs.morpheus.intermediate.Substring$;
import com.databricks.labs.morpheus.intermediate.Subtract;
import com.databricks.labs.morpheus.intermediate.SyntheticName;
import com.databricks.labs.morpheus.intermediate.TableAlias;
import com.databricks.labs.morpheus.intermediate.TableAlias$;
import com.databricks.labs.morpheus.intermediate.TableFunction;
import com.databricks.labs.morpheus.intermediate.TimestampAdd;
import com.databricks.labs.morpheus.intermediate.TimestampDiff;
import com.databricks.labs.morpheus.intermediate.TimestampDiff$;
import com.databricks.labs.morpheus.intermediate.TimestampType$;
import com.databricks.labs.morpheus.intermediate.ToNumber;
import com.databricks.labs.morpheus.intermediate.ToVarchar;
import com.databricks.labs.morpheus.intermediate.TruncDate;
import com.databricks.labs.morpheus.intermediate.TruncTimestamp;
import com.databricks.labs.morpheus.intermediate.TryCast;
import com.databricks.labs.morpheus.intermediate.TryToNumber;
import com.databricks.labs.morpheus.intermediate.TryToTimestamp;
import com.databricks.labs.morpheus.intermediate.TryToTimestamp$;
import com.databricks.labs.morpheus.intermediate.TypeOf;
import com.databricks.labs.morpheus.intermediate.UMinus;
import com.databricks.labs.morpheus.intermediate.UnBase64;
import com.databricks.labs.morpheus.intermediate.UnresolvedNamedLambdaVariable;
import com.databricks.labs.morpheus.intermediate.Uuid;
import com.databricks.labs.morpheus.intermediate.WhenBranch;
import com.databricks.labs.morpheus.parsers.SyntheticNames;
import com.databricks.labs.morpheus.transform.PartialResult;
import com.databricks.labs.morpheus.transform.PartialResult$;
import com.databricks.labs.morpheus.transform.Transformation;
import com.databricks.labs.morpheus.transform.rules.snowflake.SnowflakeTimeUnits$;
import java.io.Serializable;
import java.lang.invoke.LambdaMetafactory;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import scala.Equals;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Predef$ArrowAssoc$;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableLike;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Seq$;
import scala.collection.SeqLike;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.List$;
import scala.collection.immutable.Nil$;
import scala.collection.immutable.Set;
import scala.collection.immutable.StringOps;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.java8.JFunction0$mcI$sp;
import scala.util.Try$;

@ScalaSignature(bytes="\u0006\u0001\tMh\u0001B\u001e=\u0001-CQa\u0018\u0001\u0005\u0002\u0001Daa\u0019\u0001!\u0002\u0013!\u0007BB4\u0001A\u0003%A\rC\u0003i\u0001\u0011\u0005\u0013\u000eC\u0003v\u0001\u0011%a\u000fC\u0004\u0002\u0010\u0001!I!!\u0005\t\u000f\u0005U\u0001\u0001\"\u0003\u0002\u0018!9\u00111\u0004\u0001\u0005\n\u0005u\u0001bBA\u0012\u0001\u0011%\u0011Q\u0005\u0005\b\u0003S\u0001A\u0011BA\u0016\u0011\u001d\ty\u0003\u0001C\u0005\u0003cAq!!\u000e\u0001\t\u0013\t9\u0004C\u0004\u0002@\u0001!I!!\u0011\t\u000f\u0005\u0015\u0003\u0001\"\u0003\u0002H!9\u0011Q\n\u0001\u0005\n\u0005=\u0003bBA*\u0001\u0011%\u0011Q\u000b\u0005\b\u00037\u0002A\u0011BA/\u0011\u001d\t\u0019\u0007\u0001C\u0005\u0003KBq!!\u001b\u0001\t\u0013\tY\u0007C\u0004\u0002p\u0001!I!!\u001d\t\u000f\u0005-\u0005\u0001\"\u0003\u0002\u000e\"9\u0011\u0011\u0013\u0001\u0005\n\u0005M\u0005bBAV\u0001\u0011%\u0011Q\u0016\u0005\b\u0003\u000b\u0004A\u0011BAd\u0011\u001d\ty\r\u0001C\u0005\u0003#Dq!!6\u0001\t\u0013\t9\u000eC\u0004\u0002\\\u0002!I!!8\t\u000f\u0005\r\b\u0001\"\u0003\u0002f\"9\u0011\u0011\u001e\u0001\u0005\n\u0005-\bbBAx\u0001\u0011%\u0011\u0011\u001f\u0005\b\u0003k\u0004A\u0011BA|\u0011\u001d\ti\u0010\u0001C\u0005\u0003\u007fDqA!\u0002\u0001\t\u0013\u00119\u0001C\u0004\u0003\u000e\u0001!IAa\u0004\t\u000f\tM\u0001\u0001\"\u0003\u0003\u0016!9!1\u0004\u0001\u0005\n\tu\u0001b\u0002B\u0014\u0001\u0011%!\u0011\u0006\u0005\b\u0005_\u0001A\u0011\u0002B\u0019\u0011\u001d\u00119\u0004\u0001C\u0005\u0005sA\u0001B!\u0010\u0001A\u0003%!q\b\u0005\t\u0005/\u0002\u0001\u0015!\u0003\u0003@!9!\u0011\f\u0001\u0005\n\tm\u0003b\u0002B3\u0001\u0011%!q\r\u0005\t\u0005_\u0002\u0001\u0015!\u0003\u0003r!9!\u0011\u0010\u0001\u0005\n\tm\u0004b\u0002B@\u0001\u0011%!\u0011\u0011\u0005\b\u0005\u000f\u0003A\u0011\u0002BE\u0011\u001d\u0011i\t\u0001C\u0005\u0005\u001fCqAa%\u0001\t\u0013\u0011)\nC\u0004\u0003\u001a\u0002!IAa'\t\u000f\t}\u0005\u0001\"\u0003\u0003\"\"9!1\u0016\u0001\u0005\n\t5\u0006b\u0002BZ\u0001\u0011%!Q\u0017\u0005\b\u0005\u000f\u0004A\u0011\u0002Be\u0011\u001d\u0011i\r\u0001C\u0005\u0005\u001fDqA!8\u0001\t\u0013\u0011y\u000eC\u0004\u0003d\u0002!IA!:\t\u000f\t5\b\u0001\"\u0003\u0003p\n\u00192K\\8xM2\f7.Z\"bY2l\u0015\r\u001d9fe*\u0011QHP\u0001\ng:|wO\u001a7bW\u0016T!a\u0010!\u0002\u000bI,H.Z:\u000b\u0005\u0005\u0013\u0015!\u0003;sC:\u001chm\u001c:n\u0015\t\u0019E)\u0001\u0005n_J\u0004\b.Z;t\u0015\t)e)\u0001\u0003mC\n\u001c(BA$I\u0003)!\u0017\r^1ce&\u001c7n\u001d\u0006\u0002\u0013\u0006\u00191m\\7\u0004\u0001M)\u0001\u0001\u0014*V3B\u0011Q\nU\u0007\u0002\u001d*\u0011qJQ\u0001\rS:$XM]7fI&\fG/Z\u0005\u0003#:\u0013!bQ1mY6\u000b\u0007\u000f]3s!\ti5+\u0003\u0002U\u001d\nI\u0011J\u0015%fYB,'o\u001d\t\u0003-^k\u0011\u0001Q\u0005\u00031\u0002\u0013!\u0004\u0016:b]N4wN]7bi&|gnQ8ogR\u0014Xo\u0019;peN\u0004\"AW/\u000e\u0003mS!\u0001\u0018\"\u0002\u000fA\f'o]3sg&\u0011al\u0017\u0002\u000f'ftG\u000f[3uS\u000et\u0015-\\3t\u0003\u0019a\u0014N\\5u}Q\t\u0011\r\u0005\u0002c\u00015\tA(A\u0006{KJ|G*\u001b;fe\u0006d\u0007CA'f\u0013\t1gJA\u0004MSR,'/\u00197\u0002\u0015=tW\rT5uKJ\fG.A\u0004d_:4XM\u001d;\u0015\u0005)\u0004\bc\u0001,l[&\u0011A\u000e\u0011\u0002\u000f)J\fgn\u001d4pe6\fG/[8o!\tie.\u0003\u0002p\u001d\nQQ\t\u001f9sKN\u001c\u0018n\u001c8\t\u000bE$\u0001\u0019\u0001:\u0002\t\r\fG\u000e\u001c\t\u0003\u001bNL!\u0001\u001e(\u0003\u0005\u0019s\u0017!\u00034jqJ\u000bg\u000eZ8n)\tQw\u000fC\u0003y\u000b\u0001\u0007\u00110\u0001\u0003be\u001e\u001c\b\u0003\u0002>\u0002\n5t1a_A\u0002\u001d\tax0D\u0001~\u0015\tq(*\u0001\u0004=e>|GOP\u0005\u0003\u0003\u0003\tQa]2bY\u0006LA!!\u0002\u0002\b\u00059\u0001/Y2lC\u001e,'BAA\u0001\u0013\u0011\tY!!\u0004\u0003\u0007M+\u0017O\u0003\u0003\u0002\u0006\u0005\u001d\u0011\u0001\u0003;sk:\u001c\u0017\r^3\u0015\u0007)\f\u0019\u0002C\u0003y\r\u0001\u0007\u00110A\bpE*,7\r^\"p]N$(/^2u)\rQ\u0017\u0011\u0004\u0005\u0006q\u001e\u0001\r!_\u0001\u000b]VdG.\u00134[KJ|GcA7\u0002 !1\u0011\u0011\u0005\u0005A\u00025\fA!\u001a=qe\u0006AA-\u001b<1]VdG\u000eF\u0002n\u0003OAQ\u0001_\u0005A\u0002e\fA\u0001Z5waQ\u0019Q.!\f\t\u000baT\u0001\u0019A=\u0002/i,'o\\%oI\u0016DX\r\u001a+p\u001f:,\u0017J\u001c3fq\u0016$GcA7\u00024!1\u0011\u0011E\u0006A\u00025\fQbZ3u\u0015N|gn\u00142kK\u000e$H#\u00026\u0002:\u0005u\u0002BBA\u001e\u0019\u0001\u0007Q.\u0001\u0007gk:\u001cG/[8o\u0007\u0006dG\u000eC\u0003y\u0019\u0001\u0007\u00110A\u0003ta2LG\u000fF\u0002n\u0003\u0007BQ\u0001_\u0007A\u0002e\f\u0001\u0002^8Ok6\u0014WM\u001d\u000b\u0004[\u0006%\u0003BBA&\u001d\u0001\u0007\u00110\u0001\bbe\u001e\u001cx+\u001b;i\u0007>lW.Y:\u0002\u0017Q\u0014\u0018\u0010V8Ok6\u0014WM\u001d\u000b\u0004[\u0006E\u0003\"\u0002=\u0010\u0001\u0004I\u0018AB:ueR|7\u000eF\u0003k\u0003/\nI\u0006\u0003\u0004\u0002<A\u0001\r!\u001c\u0005\u0006qB\u0001\r!_\u0001\ngBd\u0017\u000e\u001e)beR$RA[A0\u0003CBa!a\u000f\u0012\u0001\u0004i\u0007\"\u0002=\u0012\u0001\u0004I\u0018!\u0004:fO\u0016D\b/\u0012=ue\u0006\u001cG\u000fF\u0002n\u0003OBQ\u0001\u001f\nA\u0002e\fQB]3hKb\u0004(+\u001a9mC\u000e,Gc\u00016\u0002n!)\u0001p\u0005a\u0001s\u0006AS-\\;mCR,wjY2veJ,gnY3t'V\u0004\bo\u001c:u\u0013:\u0014VmZ3yaJ+\u0007\u000f\\1dKRI!.a\u001d\u0002x\u0005m\u0014q\u0010\u0005\u0007\u0003k\"\u0002\u0019A7\u0002\u000fM,(M[3di\"1\u0011\u0011\u0010\u000bA\u00025\fq\u0001]1ui\u0016\u0014h\u000e\u0003\u0004\u0002~Q\u0001\r!\\\u0001\fe\u0016\u0004H.Y2f[\u0016tG\u000fC\u0004\u0002\u0002R\u0001\r!a!\u0002\u0013\r|g\u000eZ5uS>t\u0007CBAC\u0003\u000fkW.\u0004\u0002\u0002\b%!\u0011\u0011RA\u0004\u0005%1UO\\2uS>t\u0017'A\u0006sK\u001e,\u0007\u0010]%ogR\u0014Hc\u00016\u0002\u0010\")\u00010\u0006a\u0001s\u0006)S-\\;mCR,wjY2veJ,gnY3TkB\u0004xN\u001d;J]J+w-\u001a=q\u0013:\u001cHO\u001d\u000b\fU\u0006U\u0015qSAM\u0003;\u000b9\u000b\u0003\u0004\u0002vY\u0001\r!\u001c\u0005\u0007\u0003s2\u0002\u0019A7\t\r\u0005me\u00031\u0001n\u0003\u0019y\u0007\u000f^5p]\"9\u0011q\u0014\fA\u0002\u0005\u0005\u0016AC8dGV\u0014(/\u001a8dKB)\u0011QQAR[&!\u0011QUA\u0004\u0005\u0019y\u0005\u000f^5p]\"9\u0011\u0011\u0016\fA\u0002\u0005\u0005\u0016\u0001C4s_V\u0004h*^7\u0002?Q\u0014\u0018M\\:mCR,G*\u001b;fe\u0006d'+Z4fqB\u000b'/Y7fi\u0016\u00148\u000fF\u0003n\u0003_\u000b\u0019\rC\u0004\u00022^\u0001\r!a-\u0002\u0017I,w-\u001a=QCJ\fWn\u001d\t\u0005\u0003k\u000biL\u0004\u0003\u00028\u0006e\u0006c\u0001?\u0002\b%!\u00111XA\u0004\u0003\u0019\u0001&/\u001a3fM&!\u0011qXAa\u0005\u0019\u0019FO]5oO*!\u00111XA\u0004\u0011\u0019\tIh\u0006a\u0001[\u0006ABO]1og2\fG/\u001a*fO\u0016D\b+\u0019:b[\u0016$XM]:\u0015\u000b5\fI-!4\t\r\u0005-\u0007\u00041\u0001n\u0003=\u0011XmZ3y!\u0006\u0014\u0018-\\3uKJ\u001c\bBBA=1\u0001\u0007Q.\u0001\u0005eCR,G)\u001b4g)\rQ\u00171\u001b\u0005\u0006qf\u0001\r!_\u0001\niJLHk\u001c#bi\u0016$2!\\Am\u0011\u0015A(\u00041\u0001z\u0003\u001d!\u0017\r^3BI\u0012$RA[Ap\u0003CDa!a\u000f\u001c\u0001\u0004i\u0007\"\u0002=\u001c\u0001\u0004I\u0018\u0001\u0004;j[\u0016\u001cH/Y7q\u0003\u0012$Gc\u00016\u0002h\")\u0001\u0010\ba\u0001s\u0006AA-\u0019;f!\u0006\u0014H\u000fF\u0002k\u0003[DQ\u0001_\u000fA\u0002e\f\u0011\u0002Z1uKR\u0013XO\\2\u0015\u0007)\f\u0019\u0010C\u0003y=\u0001\u0007\u00110A\u0007nC.,G+[7fgR\fW\u000e\u001d\u000b\u0006U\u0006e\u00181 \u0005\u0007\u0003wy\u0002\u0019A7\t\u000ba|\u0002\u0019A=\u0002\rQ|G+[7f)\u0015Q'\u0011\u0001B\u0002\u0011\u0019\tY\u0004\ta\u0001[\")\u0001\u0010\ta\u0001s\u0006qAO\u001a9U_RKW.Z:uC6\u0004H#\u00026\u0003\n\t-\u0001BBA\u001eC\u0001\u0007Q\u000eC\u0003yC\u0001\u0007\u00110A\bbaBd\u0017\u0010\u0015:fG\u0016$WM\\2f)\ri'\u0011\u0003\u0005\u0007\u0003C\u0011\u0003\u0019A7\u0002\u0017Q|G+[7fgR\fW\u000e\u001d\u000b\u0006U\n]!\u0011\u0004\u0005\u0007\u0003w\u0019\u0003\u0019A7\t\u000ba\u001c\u0003\u0019A=\u00029Q|G+[7fgR\fW\u000e],ji\"d\u0015\u000e^3sC24uN]7biR)QNa\b\u0003$!1!\u0011\u0005\u0013A\u00025\f!\"\u001a=qe\u0016\u001c8/[8o\u0011\u0019\u0011)\u0003\na\u0001I\u0006\u0019a-\u001c;\u0002=Q\u0014\u0018M\\:mCR,G+Z7q_J\fGNR8s[\u0006$H*\u001b;fe\u0006dGc\u00013\u0003,!9!QF\u0013A\u0002\u0005M\u0016\u0001D:o_^4G.Y6f\r6$\u0018!\b;p)&lWm\u001d;b[B<\u0016\u000e\u001e5WCJL\u0017M\u00197f\r>\u0014X.\u0019;\u0015\u000b5\u0014\u0019D!\u000e\t\r\t\u0005b\u00051\u0001n\u0011\u0019\u0011)C\na\u0001[\u0006\tCO]1og2\fG/\u001a+f[B|'/\u00197G_Jl\u0017\r^#yaJ,7o]5p]R\u0019QNa\u000f\t\r\t5r\u00051\u0001n\u0003})hn];qa>\u0014H/\u001a3BkR|G+[7fgR\fW\u000e\u001d$pe6\fGo\u001d\t\u0007\u0005\u0003\u00129E!\u0013\u000e\u0005\t\r#\u0002\u0002B#\u0003\u000f\t!bY8mY\u0016\u001cG/[8o\u0013\u0011\tYAa\u0011\u0011\t\t-#QK\u0007\u0003\u0005\u001bRAAa\u0014\u0003R\u0005!A.\u00198h\u0015\t\u0011\u0019&\u0001\u0003kCZ\f\u0017\u0002BA`\u0005\u001b\n!$\u001e8tkB\u0004xN\u001d;fI\u0006+Ho\u001c+j[\u00164uN]7biN\f1#\u001b8gKJ$V-\u001c9pe\u0006dgi\u001c:nCR$R!\u001cB/\u0005?BaA!\t+\u0001\u0004i\u0007b\u0002B1U\u0001\u0007!1M\u0001\u0017k:\u001cX\u000f\u001d9peR,G-Q;u_\u001a|'/\\1ugB)!0!\u0003\u00024\u00061R.Y6f\u0003V$xNR8s[\u0006$X\t\u001f9mS\u000eLG\u000fF\u0003n\u0005S\u0012Y\u0007\u0003\u0004\u0002\"-\u0002\r!\u001c\u0005\b\u0005[Z\u0003\u0019AAZ\u0003aQ\u0017M^1ECR,G+[7f\r>\u0014X.\u0019;TiJLgnZ\u0001\u0016i\u0016l\u0007o\u001c:bY\u001a{'/\\1u\u001b\u0006\u0004\b/\u001b8h!\u0019\u0011\tEa\u0012\u0003tAA\u0011Q\u0011B;\u0005\u0013\u0012I%\u0003\u0003\u0003x\u0005\u001d!A\u0002+va2,''A\u0004eCft\u0017-\\3\u0015\u00075\u0014i\bC\u0003y[\u0001\u0007\u00110\u0001\u0004u_\u0012\u000bG/\u001a\u000b\u0006U\n\r%Q\u0011\u0005\u0007\u0003wq\u0003\u0019A7\t\u000bat\u0003\u0019A=\u0002\u0013%\u001c\u0018J\u001c;fO\u0016\u0014HcA7\u0003\f\")\u0001p\fa\u0001s\u00069Ao\\!se\u0006LHcA7\u0003\u0012\")\u0001\u0010\ra\u0001s\u0006IAo\u001c\"p_2,\u0017M\u001c\u000b\u0004[\n]\u0005\"\u0002=2\u0001\u0004I\u0018\u0001\u0004;ssR{'i\\8mK\u0006tGcA7\u0003\u001e\")\u0001P\ra\u0001s\u0006iAo\u001c\"p_2,\u0017M\u001c'jW\u0016$R!\u001cBR\u0005OCaA!*4\u0001\u0004i\u0017aA1sO\"1!\u0011V\u001aA\u00025\f\u0011b\u001c;iKJ<\u0018n]3\u0002\r\u0011,7m\u001c3f)\u0015Q'q\u0016BY\u0011\u0019\tY\u0004\u000ea\u0001[\")\u0001\u0010\u000ea\u0001s\u0006qQ.Y6f/\",gN\u0011:b]\u000eDG\u0003\u0003B\\\u0005{\u0013yLa1\u0011\u00075\u0013I,C\u0002\u0003<:\u0013!b\u00165f]\n\u0013\u0018M\\2i\u0011\u0019\t\t#\u000ea\u0001[\"1!\u0011Y\u001bA\u00025\fAaY8oI\"1!QY\u001bA\u00025\f1a\\;u\u0003%\t'O]1z'>\u0014H\u000fF\u0002k\u0005\u0017DQ\u0001\u001f\u001cA\u0002e\fQ\"\\1lK\u0006\u0013(/Y=T_J$Hc\u00026\u0003R\nU'\u0011\u001c\u0005\u0007\u0005'<\u0004\u0019A7\u0002\u0007\u0005\u0014(\u000fC\u0004\u0003X^\u0002\r!!)\u0002\u001bM|'\u000f^!tG\u0016tG-\u001b8h\u0011\u001d\u0011Yn\u000ea\u0001\u0003C\u000b!B\\;mYN4\u0015N]:u\u00031\u0001\u0018M]:f\u0019\u0006\u001cH\u000fR1z)\rQ'\u0011\u001d\u0005\u0006qb\u0002\r!_\u0001\ni>4\u0016M]2iCJ$RA\u001bBt\u0005SDaA!\t:\u0001\u0004i\u0007b\u0002Bvs\u0001\u0007\u0011\u0011U\u0001\u0007M>\u0014X.\u0019;\u0002\u001fQ\u0014\u0018M\\:mCR,gi\u001c:nCR$2!\u001cBy\u0011\u0019\u0011YO\u000fa\u0001[\u0002")
public class SnowflakeCallMapper
extends CallMapper
implements SyntheticNames {
    private final Literal zeroLiteral;
    private final Literal oneLiteral;
    private final Seq<String> unsupportedAutoTimestampFormats;
    private final Seq<String> unsupportedAutoTimeFormats;
    private final Seq<Tuple2<String, String>> temporalFormatMapping;

    @Override
    public Transformation<SyntheticName> freshName(String desiredName) {
        return SyntheticNames.freshName$(this, desiredName);
    }

    @Override
    public Transformation<Expression> convert(Fn call2) {
        String string;
        String string2;
        boolean bl = false;
        CallFunction callFunction = null;
        Fn fn = this.withNormalizedName(call2);
        if (fn instanceof CallFunction) {
            bl = true;
            callFunction = (CallFunction)fn;
            String string3 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_CAT".equals(string3)) {
                return this.ok(new Concat(args));
            }
        }
        if (bl) {
            String string4 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_CONSTRUCT".equals(string4)) {
                return this.ok(new CreateArray(args, CreateArray$.MODULE$.apply$default$2()));
            }
        }
        if (bl) {
            String string5 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_CONSTRUCT_COMPACT".equals(string5)) {
                return this.ok(new ArrayExcept(new CreateArray(args, CreateArray$.MODULE$.apply$default$2()), new CreateArray((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.Null()), Nil$.MODULE$), CreateArray$.MODULE$.apply$default$2())));
            }
        }
        if (bl) {
            String string6 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_CONTAINS".equals(string6)) {
                return this.ok(new ArrayContains((Expression)args.apply(true), (Expression)args.head()));
            }
        }
        if (bl) {
            String string7 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_FLATTEN".equals(string7)) {
                return this.ok(new Flatten((Expression)args.head()));
            }
        }
        if (bl) {
            String string8 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_INTERSECTION".equals(string8)) {
                return this.ok(new ArrayIntersect((Expression)args.head(), (Expression)args.apply(true)));
            }
        }
        if (bl) {
            String string9 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_SIZE".equals(string9)) {
                return this.ok(new Size((Expression)args.head()));
            }
        }
        if (bl) {
            String string10 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_SLICE".equals(string10)) {
                return this.ok(new Slice((Expression)args.head(), this.zeroIndexedToOneIndexed((Expression)args.apply(true)), (Expression)args.lift().apply(BoxesRunTime.boxToInteger(2)).getOrElse((Function0<Literal> & Serializable & scala.Serializable)() -> $this.oneLiteral)));
            }
        }
        if (bl) {
            String string11 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_SORT".equals(string11)) {
                return this.arraySort(args);
            }
        }
        if (bl) {
            String string12 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ARRAY_TO_STRING".equals(string12)) {
                return this.ok(new ArrayJoin((Expression)args.head(), (Expression)args.apply(true), None$.MODULE$));
            }
        }
        if (bl) {
            String string13 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("BASE64_DECODE_STRING".equals(string13)) {
                return this.ok(new UnBase64((Expression)args.head()));
            }
        }
        if (bl) {
            String string14 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("BASE64_DECODE_BINARY".equals(string14)) {
                return this.ok(new UnBase64((Expression)args.head()));
            }
        }
        if (bl) {
            String string15 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("BASE64_ENCODE".equals(string15)) {
                return this.ok(new Base64((Expression)args.head()));
            }
        }
        if (bl) {
            String string16 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("BITOR_AGG".equals(string16)) {
                return this.ok(new BitOrAgg((Expression)args.head()));
            }
        }
        if (bl) {
            String string17 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("BOOLAND_AGG".equals(string17)) {
                return this.ok(new BoolAnd((Expression)args.head()));
            }
        }
        if (bl) {
            String string18 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DATEADD".equals(string18)) {
                return this.dateAdd(callFunction, args);
            }
        }
        if (bl) {
            String string19 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DATEDIFF".equals(string19)) {
                return this.dateDiff(args);
            }
        }
        if (bl) {
            String string20 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DATE_FROM_PARTS".equals(string20)) {
                return this.ok(new MakeDate((Expression)args.head(), (Expression)args.apply(true), (Expression)args.apply(2)));
            }
        }
        if (bl) {
            String string21 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DATE_PART".equals(string21)) {
                return this.datePart(args);
            }
        }
        if (bl) {
            String string22 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DATE_TRUNC".equals(string22)) {
                return this.dateTrunc(args);
            }
        }
        if (bl) {
            String string23 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DAYNAME".equals(string23)) {
                return this.ok(this.dayname(args));
            }
        }
        if (bl) {
            String string24 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DECODE".equals(string24)) {
                return this.decode(callFunction, args);
            }
        }
        if (bl) {
            String string25 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DIV0".equals(string25)) {
                return this.ok(this.div0(args));
            }
        }
        if (bl) {
            String string26 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("DIV0NULL".equals(string26)) {
                return this.ok(this.div0null(args));
            }
        }
        if (bl) {
            String string27 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("EDITDISTANCE".equals(string27)) {
                return this.ok(new Levenshtein((Expression)args.head(), (Expression)args.apply(true), args.lift().apply(BoxesRunTime.boxToInteger(2))));
            }
        }
        if (bl) {
            String string28 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("FIRST_VALUE".equals(string28)) {
                return this.ok(new First((Expression)args.head(), args.lift().apply(BoxesRunTime.boxToInteger(1))));
            }
        }
        if (bl) {
            String string29 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("FLATTEN".equals(string29)) {
                return this.ok(new Explode((Expression)args.head()));
            }
        }
        if (bl) {
            String string30 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("IFNULL".equals(string30)) {
                return this.ok(new Coalesce(args));
            }
        }
        if (bl) {
            String string31 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("IS_INTEGER".equals(string31)) {
                return this.ok(this.isInteger(args));
            }
        }
        if (bl) {
            String string32 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("JSON_EXTRACT_PATH_TEXT".equals(string32)) {
                return this.getJsonObject(callFunction, args);
            }
        }
        if (bl) {
            String string33 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("LAST_DAY".equals(string33)) {
                return this.parseLastDay(args);
            }
        }
        if (bl) {
            String string34 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("LAST_VALUE".equals(string34)) {
                return this.ok(new Last((Expression)args.head(), args.lift().apply(BoxesRunTime.boxToInteger(1))));
            }
        }
        if (bl) {
            String string35 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("LEN".equals(string35)) {
                return this.ok(new Length((Expression)args.head()));
            }
        }
        if (bl) {
            String string36 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("LISTAGG".equals(string36)) {
                return this.ok(new ArrayJoin(new CollectList((Expression)args.head(), None$.MODULE$), (Expression)args.lift().apply(BoxesRunTime.boxToInteger(1)).getOrElse((Function0<Expression> & Serializable & scala.Serializable)() -> Literal$.MODULE$.apply("")), None$.MODULE$));
            }
        }
        if (bl) {
            String string37 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("MONTHNAME".equals(string37)) {
                return this.ok(new DateFormatClass((Expression)args.head(), Literal$.MODULE$.apply("MMM")));
            }
        }
        if (bl) {
            String string38 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("MONTHS_BETWEEN".equals(string38)) {
                return this.ok(new MonthsBetween((Expression)args.head(), (Expression)args.apply(true), args.lift().apply(BoxesRunTime.boxToInteger(2))));
            }
        }
        if (bl) {
            String string39 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("NULLIFZERO".equals(string39)) {
                return this.ok(this.nullIfZero((Expression)args.head()));
            }
        }
        if (bl) {
            String string40 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("OBJECT_KEYS".equals(string40)) {
                return this.ok(new JsonObjectKeys((Expression)args.head()));
            }
        }
        if (bl) {
            String string41 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("OBJECT_CONSTRUCT".equals(string41)) {
                return this.objectConstruct(args);
            }
        }
        if (bl) {
            String string42 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("PARSE_JSON".equals(string42)) {
                return this.ok(new ParseJson((Expression)args.head()));
            }
        }
        if (bl) {
            String string43 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("POSITION".equals(string43)) {
                return this.ok(new CallFunction("LOCATE", args));
            }
        }
        if (bl) {
            String string44 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("RAND".equals(string44)) {
                return this.fixRandom(args);
            }
        }
        if (bl) {
            String string45 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("RANDOM".equals(string45)) {
                return this.fixRandom(args);
            }
        }
        if (bl) {
            String string46 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("REGEXP_INSTR".equals(string46)) {
                return this.regexpInstr(args);
            }
        }
        if (bl) {
            String string47 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("REGEXP_LIKE".equals(string47)) {
                return this.ok(new RLike((Expression)args.head(), (Expression)args.apply(true)));
            }
        }
        if (bl) {
            String string48 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("REGEXP_REPLACE".equals(string48)) {
                return this.regexpReplace(args);
            }
        }
        if (bl) {
            String string49 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("REGEXP_SUBSTR".equals(string49)) {
                return this.ok(this.regexpExtract(args));
            }
        }
        if (bl) {
            String string50 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("SHA2".equals(string50)) {
                return this.ok(new Sha2((Expression)args.head(), (Expression)args.lift().apply(BoxesRunTime.boxToInteger(1)).getOrElse((Function0<Expression> & Serializable & scala.Serializable)() -> Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(256)))));
            }
        }
        if (bl) {
            String string51 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("SPLIT_PART".equals(string51)) {
                return this.splitPart(callFunction, args);
            }
        }
        if (bl) {
            String string52 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("SQUARE".equals(string52)) {
                return this.ok(new Pow((Expression)args.head(), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(2))));
            }
        }
        if (bl) {
            String string53 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("STRTOK".equals(string53)) {
                return this.strtok(callFunction, args);
            }
        }
        if (bl) {
            String string54 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("STRTOK_TO_ARRAY".equals(string54)) {
                return this.ok(this.split(args));
            }
        }
        if (bl && "SYSDATE".equals(string2 = callFunction.function_name())) {
            return this.ok(new CurrentTimestamp());
        }
        if (bl) {
            String string55 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TIMESTAMPADD".equals(string55)) {
                return this.timestampAdd(args);
            }
        }
        if (bl) {
            String string56 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TIMESTAMP_FROM_PARTS".equals(string56)) {
                return this.makeTimestamp(callFunction, args);
            }
        }
        if (bl) {
            String string57 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TIME_FROM_PARTS".equals(string57)) {
                return this.tfpToTimestamp(callFunction, args);
            }
        }
        if (bl) {
            String string58 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_ARRAY".equals(string58)) {
                return this.ok(this.toArray(args));
            }
        }
        if (bl) {
            String string59 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_BOOLEAN".equals(string59)) {
                return this.ok(this.toBoolean(args));
            }
        }
        if (bl) {
            String string60 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_DATE".equals(string60)) {
                return this.toDate(callFunction, args);
            }
        }
        if (bl) {
            String string61 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_DOUBLE".equals(string61)) {
                return this.ok(new CallFunction("DOUBLE", args));
            }
        }
        if (bl) {
            String string62 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_NUMBER".equals(string62)) {
                return this.ok(this.toNumber(args));
            }
        }
        if (bl) {
            String string63 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_OBJECT".equals(string63)) {
                return this.ok(new StructsToJson((Expression)args.head(), args.lift().apply(BoxesRunTime.boxToInteger(1))));
            }
        }
        if (bl) {
            String string64 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_VARCHAR".equals(string64)) {
                return this.toVarchar((Expression)args.head(), args.lift().apply(BoxesRunTime.boxToInteger(1)));
            }
        }
        if (bl) {
            String string65 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_VARIANT".equals(string65)) {
                return this.ok(new StructsToJson((Expression)args.head(), None$.MODULE$));
            }
        }
        if (bl) {
            String string66 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_TIME".equals(string66)) {
                return this.toTime(callFunction, args);
            }
        }
        if (bl) {
            String string67 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TO_TIMESTAMP".equals(string67)) {
                return this.toTimestamp(callFunction, args);
            }
        }
        if (bl) {
            String string68 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRUNCATE".equals(string68)) {
                return this.truncate(args);
            }
        }
        if (bl) {
            String string69 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRUNC".equals(string69)) {
                return this.truncate(args);
            }
        }
        if (bl) {
            String string70 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRY_BASE64_DECODE_STRING".equals(string70)) {
                return this.ok(new UnBase64((Expression)args.head()));
            }
        }
        if (bl) {
            String string71 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRY_BASE64_DECODE_BINARY".equals(string71)) {
                return this.ok(new UnBase64((Expression)args.head()));
            }
        }
        if (bl) {
            String string72 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRY_PARSE_JSON".equals(string72)) {
                return this.ok(new ParseJson((Expression)args.head()));
            }
        }
        if (bl) {
            String string73 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRY_TO_BOOLEAN".equals(string73)) {
                return this.ok(this.tryToBoolean(args));
            }
        }
        if (bl) {
            String string74 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRY_TO_DATE".equals(string74)) {
                return this.ok(this.tryToDate(args));
            }
        }
        if (bl) {
            String string75 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("TRY_TO_NUMBER".equals(string75)) {
                return this.ok(this.tryToNumber(args));
            }
        }
        if (bl && "UUID_STRING".equals(string = callFunction.function_name())) {
            return this.ok(new Uuid());
        }
        if (bl) {
            String string76 = callFunction.function_name();
            Seq<Expression> args = callFunction.arguments();
            if ("ZEROIFNULL".equals(string76)) {
                return this.ok(new If(new IsNull((Expression)args.head()), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(0)), (Expression)args.head()));
            }
        }
        return super.convert(fn);
    }

    private Transformation<Expression> fixRandom(Seq<Expression> args) {
        return this.ok(new Cast(new Floor(new Multiply(new Multiply(new ExpressionPrecedence(new Subtract(new Rand(args), Literal$.MODULE$.apply(BoxesRunTime.boxToDouble(0.5)))), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(2))), Literal$.MODULE$.apply(BoxesRunTime.boxToLong(Long.MAX_VALUE))), None$.MODULE$), LongType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()));
    }

    private Transformation<Expression> truncate(Seq<Expression> args) {
        if (args.size() == 1) {
            return this.ok(new Cast(new Floor((Expression)args.head(), None$.MODULE$), IntegerType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()));
        }
        if (args.size() == 2) {
            Expression expression2;
            Option<Object> option;
            Expression expression3;
            Option<String> option2;
            Tuple2 tuple2 = new Tuple2(args.head(), args.apply(true));
            if (tuple2 != null && !(option2 = StringLiteral$.MODULE$.unapply(expression3 = (Expression)tuple2._2())).isEmpty()) {
                return SnowflakeTimeUnits$.MODULE$.translateDateOrTimePart((Expression)args.apply(true)).map((Function1<String, TruncTimestamp> & Serializable & scala.Serializable)part -> new TruncTimestamp(Literal$.MODULE$.apply(part.toUpperCase()), (Expression)args.head()));
            }
            if (tuple2 != null && !(option = IntLiteral$.MODULE$.unapply(expression2 = (Expression)tuple2._2())).isEmpty()) {
                return this.ok(new Floor((Expression)args.head(), new Some<Expression>((Expression)args.apply(true))));
            }
            if (tuple2 != null) {
                return this.ok(new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new In(new TypeOf((Expression)args.head()), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("timestamp")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Comma()), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("timestamp_ntz")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Comma()), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("date")), Nil$.MODULE$)))))), new TruncTimestamp((Expression)args.apply(true), (Expression)args.head()))), Nil$.MODULE$), new Some<Expression>(new Floor((Expression)args.head(), new Some<Expression>((Expression)args.apply(true))))));
            }
            throw new MatchError(tuple2);
        }
        return this.lift(new PartialResult(args.head(), new WrongNumberOfArguments("TRUNCATE", args.size(), "1 or 2", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Transformation<Expression> objectConstruct(Seq<Expression> args) {
        Expression s2;
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some = Seq$.MODULE$.unapplySeq(seq);
        if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(1) == 0 && (s2 = (Expression)((SeqLike)some.get()).apply(0)) instanceof Star) {
            Star star = (Star)s2;
            return this.ok(new StructExpr((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)star), Nil$.MODULE$)));
        }
        if (seq != null) {
            Seq<Expression> seq2 = seq;
            Tuple2 tuple2 = seq2.sliding(2, 2).partition((Function1<Seq, Object> & Serializable & scala.Serializable)x0$1 -> BoxesRunTime.boxToBoolean(SnowflakeCallMapper.$anonfun$objectConstruct$1(x0$1)));
            if (tuple2 == null) {
                throw new MatchError(tuple2);
            }
            Iterator validPairs = tuple2._1();
            Iterator invalidPairs = tuple2._2();
            Tuple2 tuple22 = new Tuple2(validPairs, invalidPairs);
            Iterator validPairs2 = tuple22._1();
            Iterator invalidPairs2 = tuple22._2();
            StructExpr expr = new StructExpr(validPairs2.map((Function1<Seq, Alias> & Serializable & scala.Serializable)x0$2 -> {
                Seq seq = x0$2;
                Some<Seq> some = Seq$.MODULE$.unapplySeq(seq);
                if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(2) == 0) {
                    Expression expression2 = (Expression)((SeqLike)some.get()).apply(0);
                    Expression v = (Expression)((SeqLike)some.get()).apply(1);
                    Option<String> option = StringLiteral$.MODULE$.unapply(expression2);
                    if (!option.isEmpty()) {
                        String key = option.get();
                        return new Alias(v, new Id(key, Id$.MODULE$.apply$default$2()));
                    }
                }
                throw new MatchError(seq);
            }).toSeq());
            if (invalidPairs2.isEmpty()) {
                return this.ok(expr);
            }
            return this.lift(new PartialResult<StructExpr>(expr, new UnsupportedArguments("OBJECT_CONSTRUCT", args, UnsupportedArguments$.MODULE$.apply$default$3()), PartialResult$.MODULE$.apply$default$3()));
        }
        throw new MatchError(seq);
    }

    private Expression nullIfZero(Expression expr) {
        return new If(new com.databricks.labs.morpheus.intermediate.Equals(expr, this.zeroLiteral), Literal$.MODULE$.Null(), expr);
    }

    private Expression div0null(Seq<Expression> args) {
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some = Seq$.MODULE$.unapplySeq(seq);
        if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(2) == 0) {
            Expression left = (Expression)((SeqLike)some.get()).apply(0);
            Expression right = (Expression)((SeqLike)some.get()).apply(1);
            return new If(new Or(new com.databricks.labs.morpheus.intermediate.Equals(right, this.zeroLiteral), new IsNull(right)), this.zeroLiteral, new Divide(left, right));
        }
        throw new MatchError(seq);
    }

    private Expression div0(Seq<Expression> args) {
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some = Seq$.MODULE$.unapplySeq(seq);
        if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(2) == 0) {
            Expression left = (Expression)((SeqLike)some.get()).apply(0);
            Expression right = (Expression)((SeqLike)some.get()).apply(1);
            return new If(new com.databricks.labs.morpheus.intermediate.Equals(right, this.zeroLiteral), this.zeroLiteral, new Divide(left, right));
        }
        throw new MatchError(seq);
    }

    private Expression zeroIndexedToOneIndexed(Expression expr) {
        Expression expression2 = expr;
        Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
        if (!option.isEmpty()) {
            int num = BoxesRunTime.unboxToInt(option.get());
            return IntLiteral$.MODULE$.apply(num + 1);
        }
        if (expression2 instanceof UMinus) {
            UMinus uMinus = (UMinus)expression2;
            return uMinus;
        }
        return new If(new GreaterThanOrEqual(expression2, this.zeroLiteral), new Add(expression2, this.oneLiteral), expression2);
    }

    private Transformation<Expression> getJsonObject(Expression functionCall, Seq<Expression> args) {
        Transformation<Equals> transformation;
        Expression expression2;
        Option<String> option;
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some = Seq$.MODULE$.unapplySeq(seq);
        if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(2) == 0 && !(option = StringLiteral$.MODULE$.unapply(expression2 = (Expression)((SeqLike)some.get()).apply(1))).isEmpty()) {
            String path = option.get();
            transformation = this.ok(Literal$.MODULE$.apply(new StringBuilder(2).append("$.").append(path).toString()));
        } else {
            Expression id;
            Some<Seq<Expression>> some2 = Seq$.MODULE$.unapplySeq(seq);
            if (!some2.isEmpty() && some2.get() != null && ((SeqLike)some2.get()).lengthCompare(2) == 0 && (id = (Expression)((SeqLike)some2.get()).apply(1)) instanceof Id) {
                Id id2 = (Id)id;
                transformation = this.ok(new Concat((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("$.")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)id2), Nil$.MODULE$))));
            } else if (seq instanceof Fn) {
                Seq<Expression> seq2 = seq;
                transformation = this.ok(seq2);
            } else {
                transformation = this.lift(new PartialResult<Expression>(functionCall, new UnsupportedArguments("GET_JSON_OBJECT", seq, UnsupportedArguments$.MODULE$.apply$default$3()), PartialResult$.MODULE$.apply$default$3()));
            }
        }
        return transformation.map((Function1<Expression, GetJsonObject> & Serializable & scala.Serializable)translatedFmt -> new GetJsonObject((Expression)args.head(), (Expression)translatedFmt));
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private Expression split(Seq<Expression> args) {
        block5: {
            block4: {
                var4_2 = false;
                var5_3 = null;
                var6_4 = args.lift().apply(BoxesRunTime.boxToInteger(1));
                if (!None$.MODULE$.equals(var6_4)) break block4;
                v0 /* !! */  = StringLiteral$.MODULE$.apply("[ ]");
                break block5;
            }
            if (!(var6_4 instanceof Some)) ** GOTO lbl-1000
            var4_2 = true;
            var5_3 = (Some)var6_4;
            var7_5 = (Expression)var5_3.value();
            var8_6 = StringLiteral$.MODULE$.unapply(var7_5);
            if (!var8_6.isEmpty()) {
                d = var8_6.get();
                v0 /* !! */  = StringLiteral$.MODULE$.apply(new StringBuilder(2).append("[").append(d).append("]").toString());
            } else if (var4_2) {
                e = (Expression)var5_3.value();
                v0 /* !! */  = new Concat((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)StringLiteral$.MODULE$.apply("["), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)e, (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)StringLiteral$.MODULE$.apply("]"), Nil$.MODULE$))));
            } else {
                throw new MatchError(var6_4);
            }
        }
        delim = v0 /* !! */ ;
        return new StringSplit((Expression)args.head(), delim, None$.MODULE$);
    }

    private Expression toNumber(Seq<Expression> argsWithCommas) {
        Seq args = (Seq)argsWithCommas.filter((Function1<Expression, Object> & Serializable & scala.Serializable)x$2 -> BoxesRunTime.boxToBoolean(SnowflakeCallMapper.$anonfun$toNumber$1(x$2)));
        Function1 getArg = args.lift();
        if (args.size() < 2) {
            return new Cast((Expression)args.head(), DecimalType$.MODULE$.apply(38, 0), Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5());
        }
        if (args.size() == 2) {
            return new ToNumber((Expression)args.head(), (Expression)args.apply(true));
        }
        Option<Expression> fmt = getArg.apply(BoxesRunTime.boxToInteger(1)).collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                Option<String> option = StringLiteral$.MODULE$.unapply(A1);
                if (!option.isEmpty()) {
                    return (B1)A1;
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                Option<String> option = StringLiteral$.MODULE$.unapply(expression2);
                return !option.isEmpty();
            }
        });
        int precPos = BoxesRunTime.unboxToInt(fmt.fold((JFunction0$mcI$sp & scala.Serializable)() -> 1, (Function1<Expression, Object> & Serializable & scala.Serializable)x$3 -> BoxesRunTime.boxToInteger(SnowflakeCallMapper.$anonfun$toNumber$3(x$3))));
        Option<Object> prec = getArg.apply(BoxesRunTime.boxToInteger(precPos)).collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                A1 A1 = x2;
                Option<Object> option = IntLiteral$.MODULE$.unapply(A1);
                if (!option.isEmpty()) {
                    int p = BoxesRunTime.unboxToInt(option.get());
                    return (B1)BoxesRunTime.boxToInteger(p);
                }
                return function1.apply(x2);
            }

            public final boolean isDefinedAt(Expression x2) {
                Expression expression2 = x2;
                Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
                return !option.isEmpty();
            }
        });
        Option<Object> scale = getArg.apply(BoxesRunTime.boxToInteger(precPos + 1)).collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x3, Function1<A1, B1> function1) {
                A1 A1 = x3;
                Option<Object> option = IntLiteral$.MODULE$.unapply(A1);
                if (!option.isEmpty()) {
                    int s2 = BoxesRunTime.unboxToInt(option.get());
                    return (B1)BoxesRunTime.boxToInteger(s2);
                }
                return function1.apply(x3);
            }

            public final boolean isDefinedAt(Expression x3) {
                Expression expression2 = x3;
                Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
                return !option.isEmpty();
            }
        });
        Expression castedExpr = (Expression)fmt.fold((Function0<Expression> & Serializable & scala.Serializable)() -> (Expression)args.head(), (Function1<Expression, ToNumber> & Serializable & scala.Serializable)x$4 -> new ToNumber((Expression)args.head(), (Expression)args.apply(true)));
        return new Cast(castedExpr, new DecimalType(prec, scale), Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5());
    }

    private Expression tryToNumber(Seq<Expression> args) {
        Function1 getArg = args.lift();
        if (args.size() == 1) {
            return new Cast((Expression)args.head(), new DecimalType(new Some<Object>(BoxesRunTime.boxToInteger(38)), new Some<Object>(BoxesRunTime.boxToInteger(0))), Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5());
        }
        Option<Expression> fmt = getArg.apply(BoxesRunTime.boxToInteger(1)).collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x1, Function1<A1, B1> function1) {
                A1 A1 = x1;
                Option<String> option = StringLiteral$.MODULE$.unapply(A1);
                if (!option.isEmpty()) {
                    return (B1)A1;
                }
                return function1.apply(x1);
            }

            public final boolean isDefinedAt(Expression x1) {
                Expression expression2 = x1;
                Option<String> option = StringLiteral$.MODULE$.unapply(expression2);
                return !option.isEmpty();
            }
        });
        int precPos = BoxesRunTime.unboxToInt(fmt.fold((JFunction0$mcI$sp & scala.Serializable)() -> 1, (Function1<Expression, Object> & Serializable & scala.Serializable)x$5 -> BoxesRunTime.boxToInteger(SnowflakeCallMapper.$anonfun$tryToNumber$2(x$5))));
        Option<Object> prec = getArg.apply(BoxesRunTime.boxToInteger(precPos)).collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x2, Function1<A1, B1> function1) {
                A1 A1 = x2;
                Option<Object> option = IntLiteral$.MODULE$.unapply(A1);
                if (!option.isEmpty()) {
                    int p = BoxesRunTime.unboxToInt(option.get());
                    return (B1)BoxesRunTime.boxToInteger(p);
                }
                return function1.apply(x2);
            }

            public final boolean isDefinedAt(Expression x2) {
                Expression expression2 = x2;
                Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
                return !option.isEmpty();
            }
        }).orElse((Function0<Some> & Serializable & scala.Serializable)() -> new Some<Integer>(BoxesRunTime.boxToInteger(38)));
        Option<Object> scale = getArg.apply(BoxesRunTime.boxToInteger(precPos + 1)).collect(new scala.Serializable(null){
            public static final long serialVersionUID = 0L;

            public final <A1 extends Expression, B1> B1 applyOrElse(A1 x3, Function1<A1, B1> function1) {
                A1 A1 = x3;
                Option<Object> option = IntLiteral$.MODULE$.unapply(A1);
                if (!option.isEmpty()) {
                    int s2 = BoxesRunTime.unboxToInt(option.get());
                    return (B1)BoxesRunTime.boxToInteger(s2);
                }
                return function1.apply(x3);
            }

            public final boolean isDefinedAt(Expression x3) {
                Expression expression2 = x3;
                Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
                return !option.isEmpty();
            }
        }).orElse((Function0<Some> & Serializable & scala.Serializable)() -> new Some<Integer>(BoxesRunTime.boxToInteger(0)));
        Expression castedExpr = (Expression)fmt.fold((Function0<Expression> & Serializable & scala.Serializable)() -> (Expression)args.head(), (Function1<Expression, TryToNumber> & Serializable & scala.Serializable)f -> new TryToNumber((Expression)args.head(), (Expression)f));
        return new Cast(castedExpr, new DecimalType(prec, scale), Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5());
    }

    private Transformation<Expression> strtok(Expression functionCall, Seq<Expression> args) {
        if (args.size() == 1) {
            return this.splitPart(functionCall, (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)((Expression)args.head())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply(" ")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)this.oneLiteral), Nil$.MODULE$))));
        }
        if (args.size() == 2) {
            return this.splitPart(functionCall, (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)((Expression)args.head())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)((Expression)args.apply(true))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)this.oneLiteral), Nil$.MODULE$))));
        }
        return this.splitPart(functionCall, args);
    }

    private Transformation<Expression> splitPart(Expression functionCall, Seq<Expression> args) {
        Some<Seq<Expression>> some;
        Some<Seq<Expression>> some2;
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some3 = Seq$.MODULE$.unapplySeq(seq);
        if (!some3.isEmpty() && some3.get() != null && ((SeqLike)some3.get()).lengthCompare(3) == 0) {
            int n;
            Expression str = (Expression)((SeqLike)some3.get()).apply(0);
            Expression delim = (Expression)((SeqLike)some3.get()).apply(1);
            Expression expression2 = (Expression)((SeqLike)some3.get()).apply(2);
            Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
            if (!option.isEmpty() && 0 == (n = BoxesRunTime.unboxToInt(option.get()))) {
                return this.ok(new StringSplitPart(str, delim, this.oneLiteral));
            }
        }
        if (!(some2 = Seq$.MODULE$.unapplySeq(seq)).isEmpty() && some2.get() != null && ((SeqLike)some2.get()).lengthCompare(3) == 0) {
            Expression str = (Expression)((SeqLike)some2.get()).apply(0);
            Expression delim = (Expression)((SeqLike)some2.get()).apply(1);
            Expression expression3 = (Expression)((SeqLike)some2.get()).apply(2);
            Option<Object> option = IntLiteral$.MODULE$.unapply(expression3);
            if (!option.isEmpty()) {
                int p = BoxesRunTime.unboxToInt(option.get());
                return this.ok(new StringSplitPart(str, delim, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(p))));
            }
        }
        if (!(some = Seq$.MODULE$.unapplySeq(seq)).isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(3) == 0) {
            Expression str = (Expression)((SeqLike)some.get()).apply(0);
            Expression delim = (Expression)((SeqLike)some.get()).apply(1);
            Expression expr = (Expression)((SeqLike)some.get()).apply(2);
            return this.ok(new StringSplitPart(str, delim, new If(new com.databricks.labs.morpheus.intermediate.Equals(expr, this.zeroLiteral), this.oneLiteral, expr)));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("SPLIT_PART", seq.size(), "3", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    private Expression regexpExtract(Seq<Expression> args) {
        block8: {
            block7: {
                v0 /* !! */  = subject /* !! */  = args.size() >= 3 ? new Substring((Expression)args.head(), (Expression)args.apply(2), Substring$.MODULE$.apply$default$3()) : (Expression)args.head();
                if (args.size() <= 3) {
                    return new RegExpExtract(subject /* !! */ , (Expression)args.apply(true), new Some<Expression>(this.zeroLiteral));
                }
                var6_3 = (Expression)args.apply(3);
                var7_4 = IntLiteral$.MODULE$.unapply(var6_3);
                if (!var7_4.isEmpty()) {
                    o = BoxesRunTime.unboxToInt(var7_4.get());
                    v1 /* !! */  = Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(o - 1));
                } else {
                    v1 /* !! */  = new Subtract(var6_3, this.oneLiteral);
                }
                occurrence = v1 /* !! */ ;
                var10_7 = false;
                var11_8 = null;
                var12_9 = args.lift().apply(BoxesRunTime.boxToInteger(4));
                if (!None$.MODULE$.equals(var12_9)) break block7;
                v2 = (Expression)args.apply(true);
                break block8;
            }
            if (!(var12_9 instanceof Some)) ** GOTO lbl-1000
            var10_7 = true;
            var11_8 = (Some)var12_9;
            var13_10 = (Expression)var11_8.value();
            var14_11 = StringLiteral$.MODULE$.unapply(var13_10);
            if (!var14_11.isEmpty()) {
                regexParams = var14_11.get();
                v2 = this.translateLiteralRegexParameters(regexParams, (Expression)args.apply(true));
            } else if (var10_7) {
                regexParams = (Expression)var11_8.value();
                v2 = this.translateRegexParameters(regexParams, (Expression)args.apply(true));
            } else {
                throw new MatchError(var12_9);
            }
        }
        pattern = v2;
        groupNumber = args.lift().apply(BoxesRunTime.boxToInteger(5)).orElse((Function0<Some> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, ()Ljava/lang/Object;, $anonfun$regexpExtract$1(com.databricks.labs.morpheus.transform.rules.snowflake.SnowflakeCallMapper ), ()Lscala/Some;)((SnowflakeCallMapper)this));
        return new ArrayAccess(new RegExpExtractAll(subject /* !! */ , pattern, groupNumber), occurrence);
    }

    /*
     * Unable to fully structure code
     */
    private Transformation<Expression> regexpReplace(Seq<Expression> args) {
        block8: {
            block7: {
                subject = (Expression)args.head();
                var6_3 = false;
                var7_4 = null;
                var8_5 = args.lift().apply(BoxesRunTime.boxToInteger(5));
                if (!None$.MODULE$.equals(var8_5)) break block7;
                v0 = (Expression)args.apply(true);
                break block8;
            }
            if (!(var8_5 instanceof Some)) ** GOTO lbl-1000
            var6_3 = true;
            var7_4 = (Some)var8_5;
            var9_6 = (Expression)var7_4.value();
            var10_7 = StringLiteral$.MODULE$.unapply(var9_6);
            if (!var10_7.isEmpty()) {
                regexParams = var10_7.get();
                v0 = this.translateLiteralRegexParameters(regexParams, (Expression)args.apply(true));
            } else if (var6_3) {
                regexParams = (Expression)var7_4.value();
                v0 = this.translateRegexParameters(regexParams, (Expression)args.apply(true));
            } else {
                throw new MatchError(var8_5);
            }
        }
        pattern = v0;
        replacement = (Expression)args.lift().apply(BoxesRunTime.boxToInteger(2)).getOrElse((Function0<Expression> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, ()Ljava/lang/Object;, $anonfun$regexpReplace$1(), ()Lcom/databricks/labs/morpheus/intermediate/Expression;)());
        position = args.lift().apply(BoxesRunTime.boxToInteger(3));
        expr = new RegExpReplace(subject, pattern, replacement, position);
        if (args.size() <= 4) {
            return this.ok(expr);
        }
        var16_14 = (Expression)args.apply(4);
        var17_15 = IntLiteral$.MODULE$.unapply(var16_14);
        if (!var17_15.isEmpty() && 0 == (var18_16 = BoxesRunTime.unboxToInt(var17_15.get()))) {
            return this.ok(expr);
        }
        var19_17 = IntLiteral$.MODULE$.unapply(var16_14);
        if (!var19_17.isEmpty()) {
            occurrence = BoxesRunTime.unboxToInt(var19_17.get());
            return this.emulateOccurrencesSupportInRegexpReplace(subject, pattern, replacement, (Function1<Expression, Expression>)(Function1<Expression, com.databricks.labs.morpheus.intermediate.Equals> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, $anonfun$regexpReplace$2(int com.databricks.labs.morpheus.intermediate.Expression ), (Lcom/databricks/labs/morpheus/intermediate/Expression;)Lcom/databricks/labs/morpheus/intermediate/Equals;)((int)occurrence));
        }
        return this.emulateOccurrencesSupportInRegexpReplace(subject, pattern, replacement, (Function1<Expression, Expression>)(Function1<Expression, Or> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, $anonfun$regexpReplace$3(com.databricks.labs.morpheus.intermediate.Expression com.databricks.labs.morpheus.intermediate.Expression ), (Lcom/databricks/labs/morpheus/intermediate/Expression;)Lcom/databricks/labs/morpheus/intermediate/Or;)((Expression)var16_14));
    }

    private Transformation<Expression> emulateOccurrencesSupportInRegexpReplace(Expression subject, Expression pattern, Expression replacement, Function1<Expression, Expression> condition) {
        return this.freshName("t").flatMap((Function1<SyntheticName, Transformation> & Serializable & scala.Serializable)tableName -> this.freshName("x").map((Function1<SyntheticName, ScalarSubquery> & Serializable & scala.Serializable)lambdaVariable -> new ScalarSubquery(new Project(new TableAlias(new TableFunction(new PosExplode(new ArraysZip((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new RegExpExtractAll(subject, pattern, RegExpExtractAll$.MODULE$.apply$default$3())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new StringSplit(subject, pattern, StringSplit$.MODULE$.apply$default$3())), Nil$.MODULE$))))), (Expression)tableName, TableAlias$.MODULE$.apply$default$3()), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new ArrayJoin(new ArrayTransform(new ArraySort(new CollectList(new StructExpr((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)tableName, new Id("pos", Id$.MODULE$.apply$default$2()))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)tableName, new Id("col", Id$.MODULE$.apply$default$2()))), Nil$.MODULE$))), CollectList$.MODULE$.apply$default$2()), ArraySort$.MODULE$.apply$default$2()), new LambdaFunction(new If((Expression)condition.apply((Expression)lambdaVariable), new Concat((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)lambdaVariable, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("1", true)))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)replacement), Nil$.MODULE$))), new Concat((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)lambdaVariable, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("1", true)))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Coalesce((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)lambdaVariable, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("0", true)))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("")), Nil$.MODULE$)))), Nil$.MODULE$)))), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)lambdaVariable), Nil$.MODULE$))), Literal$.MODULE$.apply(""), ArrayJoin$.MODULE$.apply$default$3())), Nil$.MODULE$)))));
    }

    /*
     * Unable to fully structure code
     */
    private Transformation<Expression> regexpInstr(Seq<Expression> args) {
        block13: {
            block12: {
                block8: {
                    block11: {
                        block10: {
                            block9: {
                                block7: {
                                    var5_2 = new Tuple2<A, Option<B>>(args.head(), args.lift().apply(BoxesRunTime.boxToInteger(2)));
                                    if (var5_2 == null) break block7;
                                    s = (Expression)var5_2._1();
                                    var7_4 = var5_2._2();
                                    if (!None$.MODULE$.equals(var7_4)) break block7;
                                    v0 = s;
                                    break block8;
                                }
                                if (var5_2 == null) break block9;
                                var8_5 = (Expression)var5_2._1();
                                var9_6 = var5_2._2();
                                var10_7 = StringLiteral$.MODULE$.unapply(var8_5);
                                if (var10_7.isEmpty()) break block9;
                                s = var10_7.get();
                                if (!(var9_6 instanceof Some) || (var14_11 = IntLiteral$.MODULE$.unapply(var13_10 = (Expression)(var12_9 = (Some)var9_6).value())).isEmpty()) break block9;
                                p = BoxesRunTime.unboxToInt(var14_11.get());
                                v0 = Literal$.MODULE$.apply(new StringOps(Predef$.MODULE$.augmentString(s)).drop(p - 1));
                                break block8;
                            }
                            if (var5_2 == null) break block10;
                            s = (Expression)var5_2._1();
                            var17_14 = var5_2._2();
                            if (!(var17_14 instanceof Some) || (var20_17 = IntLiteral$.MODULE$.unapply(var19_16 = (Expression)(var18_15 = (Some)var17_14).value())).isEmpty() || 1 != (var21_18 = BoxesRunTime.unboxToInt(var20_17.get()))) break block10;
                            v0 = s;
                            break block8;
                        }
                        if (var5_2 == null) break block11;
                        s = (Expression)var5_2._1();
                        var23_20 = var5_2._2();
                        if (!(var23_20 instanceof Some) || (var26_23 = IntLiteral$.MODULE$.unapply(var25_22 = (Expression)(var24_21 = (Some)var23_20).value())).isEmpty()) break block11;
                        p = BoxesRunTime.unboxToInt(var26_23.get());
                        v0 = new Substring(s, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(p - 1)), Substring$.MODULE$.apply$default$3());
                        break block8;
                    }
                    if (var5_2 == null) ** GOTO lbl-1000
                    s = (Expression)var5_2._1();
                    var29_26 = var5_2._2();
                    if (var29_26 instanceof Some) {
                        var30_27 = (Some)var29_26;
                        p = (Expression)var30_27.value();
                        v0 = new Substring(s, new Subtract(p, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(1))), Substring$.MODULE$.apply$default$3());
                    } else lbl-1000:
                    // 2 sources

                    {
                        throw new MatchError(var5_2);
                    }
                }
                subject = v0;
                var33_30 = false;
                var34_31 = null;
                var35_32 = args.lift().apply(BoxesRunTime.boxToInteger(5));
                if (!None$.MODULE$.equals(var35_32)) break block12;
                v1 = (Expression)args.apply(true);
                break block13;
            }
            if (!(var35_32 instanceof Some)) ** GOTO lbl-1000
            var33_30 = true;
            var34_31 = (Some)var35_32;
            var36_33 = (Expression)var34_31.value();
            var37_34 = StringLiteral$.MODULE$.unapply(var36_33);
            if (!var37_34.isEmpty()) {
                regexParams = var37_34.get();
                v1 = this.translateLiteralRegexParameters(regexParams, (Expression)args.apply(true));
            } else if (var33_30) {
                regexParams = (Expression)var34_31.value();
                v1 = this.translateRegexParameters(regexParams, (Expression)args.apply(true));
            } else {
                throw new MatchError(var35_32);
            }
        }
        pattern = v1;
        if (args.size() < 4) {
            return this.ok(new RegexpInstr(subject, pattern));
        }
        option = (Expression)args.lift().apply(BoxesRunTime.boxToInteger(4)).getOrElse((Function0<Expression> & Serializable & scala.Serializable)LambdaMetafactory.altMetafactory(null, null, null, ()Ljava/lang/Object;, $anonfun$regexpInstr$1(), ()Lcom/databricks/labs/morpheus/intermediate/Expression;)());
        groupNum = args.lift().apply(BoxesRunTime.boxToInteger(6));
        return this.emulateOccurrenceSupportInRegexpInstr(subject, pattern, option, args.lift().apply(BoxesRunTime.boxToInteger(3)), groupNum);
    }

    private Transformation<Expression> emulateOccurrenceSupportInRegexpInstr(Expression subject, Expression pattern, Expression option, Option<Expression> occurrence, Option<Expression> groupNum) {
        return this.freshName("t").flatMap((Function1<SyntheticName, Transformation> & Serializable & scala.Serializable)tableName -> this.freshName("acc").flatMap((Function1<SyntheticName, Transformation> & Serializable & scala.Serializable)accumulator -> this.freshName("x").map((Function1<SyntheticName, ScalarSubquery> & Serializable & scala.Serializable)item -> {
            TableAlias explodeTable = new TableAlias(new TableFunction(new PosExplode(new ArraysZip((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new RegExpExtractAll(subject, pattern, groupNum)), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new StringSplit(subject, pattern, StringSplit$.MODULE$.apply$default$3())), Nil$.MODULE$))))), (Expression)tableName, TableAlias$.MODULE$.apply$default$3());
            return new ScalarSubquery(new Project((LogicalPlan)occurrence.map((Function1<Expression, Filter> & Serializable & scala.Serializable)occ -> new Filter(explodeTable, new LessThan(new Dot((Expression)tableName, new Id("pos", Id$.MODULE$.apply$default$2())), (Expression)occ))).getOrElse((Function0<TableAlias> & Serializable & scala.Serializable)() -> explodeTable), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new ArrayAggregate(new CollectList(new StructExpr((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)tableName, new Id("pos", Id$.MODULE$.apply$default$2()))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Dot((Expression)tableName, new Id("col", Id$.MODULE$.apply$default$2()))), Nil$.MODULE$))), CollectList$.MODULE$.apply$default$2()), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(1)), new LambdaFunction(new Case(new Some<Expression>(option), (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(0)), new Add((Expression)accumulator, new Length(new Dot((Expression)item, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("1", true))))))), Nil$.MODULE$), new Some<Expression>(new Add((Expression)accumulator, new Add(new Length(new Dot((Expression)item, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("0", true)))), new Length(new Dot((Expression)item, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("1", true)))))))), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)accumulator), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)item), Nil$.MODULE$))), ArrayAggregate$.MODULE$.apply$default$4())), Nil$.MODULE$)));
        })));
    }

    private Expression translateLiteralRegexParameters(String regexParams, Expression pattern) {
        String filtered = new StringOps(Predef$.MODULE$.augmentString(regexParams)).foldLeft("", (Function2<String, Object, String> & Serializable & scala.Serializable)(x0$1, x1$1) -> SnowflakeCallMapper.$anonfun$translateLiteralRegexParameters$1(x0$1, BoxesRunTime.unboxToChar(x1$1)));
        if (new StringOps(Predef$.MODULE$.augmentString(filtered)).nonEmpty()) {
            Expression expression2 = pattern;
            Option<String> option = StringLiteral$.MODULE$.unapply(expression2);
            if (!option.isEmpty()) {
                String pat = option.get();
                return Literal$.MODULE$.apply(new StringBuilder(3).append("(?").append(filtered).append(")").append(pat).toString());
            }
            return new Concat((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply(new StringBuilder(3).append("(?").append(filtered).append(")").toString())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)expression2), Nil$.MODULE$)));
        }
        return pattern;
    }

    private Expression translateRegexParameters(Expression regexParameters, Expression pattern) {
        return new ArrayAggregate(new StringSplit(regexParameters, Literal$.MODULE$.apply(""), None$.MODULE$), new Cast(new CreateArray(Nil$.MODULE$, CreateArray$.MODULE$.apply$default$2()), new ArrayType(StringType$.MODULE$), Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()), new LambdaFunction(new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new com.databricks.labs.morpheus.intermediate.Equals(new Id("item", Id$.MODULE$.apply$default$2()), Literal$.MODULE$.apply("c")), new ArrayFilter(new Id("agg", Id$.MODULE$.apply$default$2()), new LambdaFunction(new NotEquals(new Id("item", Id$.MODULE$.apply$default$2()), Literal$.MODULE$.apply("i")), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"item"), Nil$.MODULE$))), Nil$.MODULE$))))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new In(new Id("item", Id$.MODULE$.apply$default$2()), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("i")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Comma()), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("s")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new Comma()), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("m")), Nil$.MODULE$)))))), new ArrayAppend(new Id("agg", Id$.MODULE$.apply$default$2()), new Id("item", Id$.MODULE$.apply$default$2())))), Nil$.MODULE$)), new Some<Expression>(new Id("agg", Id$.MODULE$.apply$default$2()))), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"agg"), Nil$.MODULE$))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"item"), Nil$.MODULE$))), Nil$.MODULE$))), new Some<Expression>(new LambdaFunction(new Concat((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("(?")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new ArrayJoin(new Id("filtered", Id$.MODULE$.apply$default$2()), Literal$.MODULE$.apply(""), ArrayJoin$.MODULE$.apply$default$3())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply(")")), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)pattern), Nil$.MODULE$))))), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"filtered"), Nil$.MODULE$))), Nil$.MODULE$))));
    }

    private Transformation<Expression> dateDiff(Seq<Expression> args) {
        return SnowflakeTimeUnits$.MODULE$.translateDateOrTimePart((Expression)args.head()).map((Function1<String, TimestampDiff> & Serializable & scala.Serializable)datePart -> new TimestampDiff((String)datePart, (Expression)args.apply(true), (Expression)args.apply(2), TimestampDiff$.MODULE$.apply$default$4()));
    }

    private Expression tryToDate(Seq<Expression> args) {
        return new CallFunction("DATE", (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new TryToTimestamp((Expression)args.head(), args.lift().apply(BoxesRunTime.boxToInteger(1)))), Nil$.MODULE$));
    }

    private Transformation<Expression> dateAdd(Expression functionCall, Seq<Expression> args) {
        if (args.size() == 2) {
            return this.ok(new DateAdd((Expression)args.head(), (Expression)args.apply(true)));
        }
        if (args.size() == 3) {
            return this.timestampAdd(args);
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("DATEADD", args.size(), "2 or 3", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Transformation<Expression> timestampAdd(Seq<Expression> args) {
        return SnowflakeTimeUnits$.MODULE$.translateDateOrTimePart((Expression)args.head()).map((Function1<String, TimestampAdd> & Serializable & scala.Serializable)dateOrTimePart -> new TimestampAdd((String)dateOrTimePart, (Expression)args.apply(true), (Expression)args.apply(2)));
    }

    private Transformation<Expression> datePart(Seq<Expression> args) {
        return SnowflakeTimeUnits$.MODULE$.translateDateOrTimePart((Expression)args.head()).map((Function1<String, Extract> & Serializable & scala.Serializable)part -> new Extract(new Id((String)part, Id$.MODULE$.apply$default$2()), (Expression)args.apply(true)));
    }

    private Transformation<Expression> dateTrunc(Seq<Expression> args) {
        return SnowflakeTimeUnits$.MODULE$.translateDateOrTimePart((Expression)args.head()).map((Function1<String, TruncTimestamp> & Serializable & scala.Serializable)part -> new TruncTimestamp(Literal$.MODULE$.apply(part.toUpperCase()), (Expression)args.apply(true)));
    }

    private Transformation<Expression> makeTimestamp(Expression functionCall, Seq<Expression> args) {
        if (args.size() == 2) {
            DatePart year = new DatePart(new Id("year", Id$.MODULE$.apply$default$2()), (Expression)args.head());
            DatePart month = new DatePart(new Id("month", Id$.MODULE$.apply$default$2()), (Expression)args.head());
            DatePart day = new DatePart(new Id("day", Id$.MODULE$.apply$default$2()), (Expression)args.head());
            Hour hour = new Hour((Expression)args.apply(true));
            Minute minute = new Minute((Expression)args.apply(true));
            Second second = new Second((Expression)args.apply(true));
            return this.ok(new MakeTimestamp(year, month, day, hour, minute, second, None$.MODULE$));
        }
        if (args.size() == 6) {
            return this.ok(new MakeTimestamp((Expression)args.head(), (Expression)args.apply(true), (Expression)args.apply(2), (Expression)args.apply(3), (Expression)args.apply(4), (Expression)args.apply(5), None$.MODULE$));
        }
        if (args.size() == 7) {
            Expression expression2 = (Expression)args.apply(6);
            Option<Object> option = IntLiteral$.MODULE$.unapply(expression2);
            if (!option.isEmpty()) {
                return this.ok(new MakeTimestamp((Expression)args.head(), (Expression)args.apply(true), (Expression)args.apply(2), (Expression)args.apply(3), (Expression)args.apply(4), (Expression)args.apply(5), None$.MODULE$));
            }
            Option<String> option2 = StringLiteral$.MODULE$.unapply(expression2);
            if (!option2.isEmpty()) {
                return this.ok(new MakeTimestamp((Expression)args.head(), (Expression)args.apply(true), (Expression)args.apply(2), (Expression)args.apply(3), (Expression)args.apply(4), (Expression)args.apply(5), new Some<Expression>(expression2)));
            }
            return this.lift(new PartialResult<Expression>(functionCall, new UnsupportedArguments("TIMESTAMP_FROM_PARTS", (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)((Expression)args.apply(6))), Nil$.MODULE$), UnsupportedArguments$.MODULE$.apply$default$3()), PartialResult$.MODULE$.apply$default$3()));
        }
        if (args.size() == 8) {
            return this.ok(new MakeTimestamp((Expression)args.head(), (Expression)args.apply(true), (Expression)args.apply(2), (Expression)args.apply(3), (Expression)args.apply(4), (Expression)args.apply(5), new Some<Expression>((Expression)args.apply(7))));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("TIMESTAMP_FROM_PARTS", args.size(), "either 2, 6, 7 or 8", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Transformation<Expression> toTime(Expression functionCall, Seq<Expression> args) {
        Expression timeFormat = Literal$.MODULE$.apply("HH:mm:ss");
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some = Seq$.MODULE$.unapplySeq(seq);
        if (!some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(1) == 0) {
            Expression a = (Expression)((SeqLike)some.get()).apply(0);
            return this.ok(new DateFormatClass(this.inferTemporalFormat(a, this.unsupportedAutoTimestampFormats.$plus$plus(this.unsupportedAutoTimeFormats, Seq$.MODULE$.canBuildFrom())), timeFormat));
        }
        Some<Seq<Expression>> some2 = Seq$.MODULE$.unapplySeq(seq);
        if (!some2.isEmpty() && some2.get() != null && ((SeqLike)some2.get()).lengthCompare(2) == 0) {
            Expression a = (Expression)((SeqLike)some2.get()).apply(0);
            Expression b = (Expression)((SeqLike)some2.get()).apply(1);
            return this.ok(new DateFormatClass(new ParseToTimestamp(a, new Some<Expression>(b)), timeFormat));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("TO_TIMESTAMP", args.size(), "1 or 2", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Transformation<Expression> tfpToTimestamp(Expression functionCall, Seq<Expression> args) {
        if (args.size() == 3) {
            return this.ok(new Cast(new Mod(new ExpressionPrecedence(new Add(new Add(new Multiply(this.applyPrecedence((Expression)args.head()), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(3600))), new Multiply(this.applyPrecedence((Expression)args.apply(true)), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(60)))), this.applyPrecedence((Expression)args.apply(2)))), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(86400))), TimestampType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()));
        }
        if (args.size() == 4) {
            return this.ok(new Cast(new Mod(new ExpressionPrecedence(new Add(new Add(new Add(new Multiply(this.applyPrecedence((Expression)args.head()), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(3600))), new Multiply(this.applyPrecedence((Expression)args.apply(true)), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(60)))), this.applyPrecedence(this.applyPrecedence((Expression)args.apply(2)))), new Divide(this.applyPrecedence((Expression)args.apply(3)), Literal$.MODULE$.apply(BoxesRunTime.boxToFloat(1.0E9f))))), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(86400))), TimestampType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("TIME_FROM_PARTS", args.size(), "3 or 4", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Expression applyPrecedence(Expression expr) {
        Expression expression2 = expr;
        if (expression2 instanceof Literal) {
            return expr;
        }
        if (expression2 instanceof ExpressionPrecedence) {
            return expr;
        }
        return new ExpressionPrecedence(expr);
    }

    private Transformation<Expression> toTimestamp(Expression functionCall, Seq<Expression> args) {
        Some<Seq<Expression>> some;
        Seq<Expression> seq = args;
        Some<Seq<Expression>> some2 = Seq$.MODULE$.unapplySeq(seq);
        if (!some2.isEmpty() && some2.get() != null && ((SeqLike)some2.get()).lengthCompare(1) == 0) {
            Expression a = (Expression)((SeqLike)some2.get()).apply(0);
            return this.ok(this.inferTemporalFormat(a, this.unsupportedAutoTimestampFormats));
        }
        Some<Seq<Expression>> some3 = Seq$.MODULE$.unapplySeq(seq);
        if (!some3.isEmpty() && some3.get() != null && ((SeqLike)some3.get()).lengthCompare(2) == 0) {
            Expression a = (Expression)((SeqLike)some3.get()).apply(0);
            Expression lit = (Expression)((SeqLike)some3.get()).apply(1);
            if (lit instanceof Literal) {
                Literal literal = (Literal)lit;
                return this.ok(this.toTimestampWithLiteralFormat(a, literal));
            }
        }
        if (!(some = Seq$.MODULE$.unapplySeq(seq)).isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(2) == 0) {
            Expression a = (Expression)((SeqLike)some.get()).apply(0);
            Expression b = (Expression)((SeqLike)some.get()).apply(1);
            return this.ok(this.toTimestampWithVariableFormat(a, b));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("TO_TIMESTAMP", args.size(), "1 or 2", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Expression toTimestampWithLiteralFormat(Expression expression2, Literal fmt) {
        Literal literal = fmt;
        Option<Object> option = IntLiteral$.MODULE$.unapply(literal);
        if (!option.isEmpty()) {
            return new ParseToTimestamp(expression2, new Some<Expression>(new Pow(Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(10)), literal)));
        }
        Option<String> option2 = StringLiteral$.MODULE$.unapply(literal);
        if (!option2.isEmpty()) {
            String str = option2.get();
            return new ParseToTimestamp(expression2, new Some<Expression>(this.translateTemporalFormatLiteral(str)));
        }
        throw new MatchError(literal);
    }

    private Literal translateTemporalFormatLiteral(String snowflakeFmt) {
        return StringLiteral$.MODULE$.apply(this.temporalFormatMapping.foldLeft(snowflakeFmt, (Function2<String, Tuple2, String> & Serializable & scala.Serializable)(x0$1, x1$1) -> {
            Tuple2<String, Tuple2> tuple2 = new Tuple2<String, Tuple2>((String)x0$1, (Tuple2)x1$1);
            if (tuple2 != null) {
                String s2 = tuple2._1();
                Tuple2 tuple22 = tuple2._2();
                if (tuple22 != null) {
                    String sf = (String)tuple22._1();
                    String dbx = (String)tuple22._2();
                    return s2.replace(sf, dbx);
                }
            }
            throw new MatchError(tuple2);
        }));
    }

    private Expression toTimestampWithVariableFormat(Expression expression2, Expression fmt) {
        Expression translatedFmt = this.translateTemporalFormatExpression(fmt);
        return new If(new StartsWith(fmt, Literal$.MODULE$.apply("DY")), new ParseToTimestamp(new Substring(expression2, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(4)), Substring$.MODULE$.apply$default$3()), new Some<Expression>(new Substring(translatedFmt, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(4)), Substring$.MODULE$.apply$default$3()))), new ParseToTimestamp(expression2, new Some<Expression>(translatedFmt)));
    }

    private Expression translateTemporalFormatExpression(Expression snowflakeFmt) {
        return this.temporalFormatMapping.foldLeft(snowflakeFmt, (Function2<Expression, Tuple2, Expression> & Serializable & scala.Serializable)(x0$1, x1$1) -> {
            Tuple2<Expression, Tuple2> tuple2 = new Tuple2<Expression, Tuple2>((Expression)x0$1, (Tuple2)x1$1);
            if (tuple2 != null) {
                Expression s2 = tuple2._1();
                Tuple2 tuple22 = tuple2._2();
                if (tuple22 != null) {
                    String sf = (String)tuple22._1();
                    String dbx = (String)tuple22._2();
                    return new StringReplace(s2, Literal$.MODULE$.apply(sf), new Some<Expression>(Literal$.MODULE$.apply(dbx)));
                }
            }
            throw new MatchError(tuple2);
        });
    }

    private Expression inferTemporalFormat(Expression expression2, Seq<String> unsupportedAutoformats) {
        Expression expression3 = expression2;
        Option<String> option = StringLiteral$.MODULE$.unapply(expression3);
        if (!option.isEmpty()) {
            String timeStr = option.get();
            return (Expression)Try$.MODULE$.apply((JFunction0$mcI$sp & scala.Serializable)() -> new StringOps(Predef$.MODULE$.augmentString(timeStr.trim())).toInt()).map((Function1<Object, ParseToTimestamp> & Serializable & scala.Serializable)object -> SnowflakeCallMapper.$anonfun$inferTemporalFormat$2(BoxesRunTime.unboxToInt(object))).getOrElse((Function0<ParseToTimestamp> & Serializable & scala.Serializable)() -> new ParseToTimestamp(expression2, unsupportedAutoformats.find((Function1<String, Object> & Serializable & scala.Serializable)fmt -> BoxesRunTime.boxToBoolean(SnowflakeCallMapper.$anonfun$inferTemporalFormat$4(timeStr, fmt))).map((Function1<String, Expression> & Serializable & scala.Serializable)x$7 -> Literal$.MODULE$.apply(x$7))));
        }
        TryToTimestamp tryToTimestamp = new TryToTimestamp(new TryCast(expression3, IntegerType$.MODULE$), TryToTimestamp$.MODULE$.apply$default$2());
        return new Case(new Some<Expression>(new TypeOf(expression3)), (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(Literal$.MODULE$.apply("string"), new IfNull(new Coalesce(((SeqLike)unsupportedAutoformats.map((Function1<String, Expression> & Serializable & scala.Serializable)x$8 -> this.makeAutoFormatExplicit(expression3, (String)x$8), Seq$.MODULE$.canBuildFrom())).$plus$colon(tryToTimestamp, Seq$.MODULE$.canBuildFrom())), new ParseToTimestamp(expression3, ParseToTimestamp$.MODULE$.apply$default$2())))), Nil$.MODULE$), new Some<Expression>(new Cast(expression2, TimestampType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5())));
    }

    private Expression makeAutoFormatExplicit(Expression expr, String javaDateTimeFormatString) {
        if (javaDateTimeFormatString.startsWith("EEE")) {
            return new TryToTimestamp(new Substring(expr, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(4)), Substring$.MODULE$.apply$default$3()), new Some<Expression>(Literal$.MODULE$.apply(javaDateTimeFormatString.substring(3))));
        }
        return new TryToTimestamp(expr, new Some<Expression>(Literal$.MODULE$.apply(javaDateTimeFormatString)));
    }

    private Expression dayname(Seq<Expression> args) {
        return new DateFormatClass((Expression)args.head(), Literal$.MODULE$.apply("E"));
    }

    private Transformation<Expression> toDate(Expression functionCall, Seq<Expression> args) {
        if (args.size() == 1) {
            return this.ok(new Cast((Expression)args.head(), DateType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5()));
        }
        if (args.size() == 2) {
            return this.ok(new ParseToDate((Expression)args.head(), new Some<Expression>((Expression)args.apply(true))));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("TO_DATE", args.size(), "1 or 2", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Expression isInteger(Seq<Expression> args) {
        return new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new IsNull((Expression)args.head()), Literal$.MODULE$.Null())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new And(new RLike((Expression)args.head(), Literal$.MODULE$.apply("^-?[0-9]+$")), new IsNotNull(new TryCast((Expression)args.head(), IntegerType$.MODULE$))), Literal$.MODULE$.apply(BoxesRunTime.boxToBoolean(true)))), Nil$.MODULE$)), new Some<Expression>(Literal$.MODULE$.apply(BoxesRunTime.boxToBoolean(false))));
    }

    private Expression toArray(Seq<Expression> args) {
        return new If(new IsNull((Expression)args.head()), Literal$.MODULE$.Null(), new CreateArray((Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)((Expression)args.head())), Nil$.MODULE$), CreateArray$.MODULE$.apply$default$2()));
    }

    private Expression toBoolean(Seq<Expression> args) {
        return this.toBooleanLike((Expression)args.head(), new RaiseError(Literal$.MODULE$.apply("Invalid parameter type for TO_BOOLEAN")));
    }

    private Expression tryToBoolean(Seq<Expression> args) {
        return this.toBooleanLike((Expression)args.head(), Literal$.MODULE$.Null());
    }

    private Expression toBooleanLike(Expression arg, Expression otherwise) {
        Cast castArgAsDouble = new Cast(arg, DoubleType$.MODULE$, Cast$.MODULE$.apply$default$3(), Cast$.MODULE$.apply$default$4(), Cast$.MODULE$.apply$default$5());
        return new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new IsNull(arg), Literal$.MODULE$.Null())), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new com.databricks.labs.morpheus.intermediate.Equals(new TypeOf(arg), Literal$.MODULE$.apply("boolean")), new CallFunction("BOOLEAN", (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)arg), Nil$.MODULE$)))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new com.databricks.labs.morpheus.intermediate.Equals(new TypeOf(arg), Literal$.MODULE$.apply("string")), new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new In(new Lower(arg), (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Expression[]{Literal$.MODULE$.apply("true"), new Comma(), Literal$.MODULE$.apply("t"), new Comma(), Literal$.MODULE$.apply("yes"), new Comma(), Literal$.MODULE$.apply("y"), new Comma(), Literal$.MODULE$.apply("on"), new Comma(), Literal$.MODULE$.apply("1")}))), Literal$.MODULE$.apply(BoxesRunTime.boxToBoolean(true)))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new In(new Lower(arg), (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Expression[]{Literal$.MODULE$.apply("false"), new Comma(), Literal$.MODULE$.apply("f"), new Comma(), Literal$.MODULE$.apply("no"), new Comma(), Literal$.MODULE$.apply("n"), new Comma(), Literal$.MODULE$.apply("off"), new Comma(), Literal$.MODULE$.apply("0")}))), Literal$.MODULE$.apply(BoxesRunTime.boxToBoolean(false)))), Nil$.MODULE$)), new Some<Expression>(new RaiseError(Literal$.MODULE$.apply("Boolean value of x is not recognized by TO_BOOLEAN")))))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new IsNotNull(new TryCast(arg, DoubleType$.MODULE$)), new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new Or(new IsNaN(castArgAsDouble), new com.databricks.labs.morpheus.intermediate.Equals(castArgAsDouble, new CallFunction("DOUBLE", (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)Literal$.MODULE$.apply("infinity")), Nil$.MODULE$)))), new RaiseError(Literal$.MODULE$.apply("Invalid parameter type for TO_BOOLEAN")))), Nil$.MODULE$), new Some<Expression>(new NotEquals(castArgAsDouble, DoubleLiteral$.MODULE$.apply(0.0)))))), Nil$.MODULE$)))), new Some<Expression>(otherwise));
    }

    private Transformation<Expression> decode(Expression functionCall, Seq<Expression> args) {
        if (args.size() >= 3) {
            Expression expr = (Expression)args.head();
            List groupedArgs = ((IterableLike)args.tail()).sliding(2, 2).toList();
            return this.ok(new Case(None$.MODULE$, ((List)groupedArgs.takeWhile((Function1<Seq, Object> & Serializable & scala.Serializable)x$10 -> BoxesRunTime.boxToBoolean(SnowflakeCallMapper.$anonfun$decode$1(x$10)))).map((Function1<Seq, WhenBranch> & Serializable & scala.Serializable)l -> this.makeWhenBranch(expr, (Expression)l.head(), (Expression)l.last()), List$.MODULE$.canBuildFrom()), groupedArgs.find((Function1<Seq, Object> & Serializable & scala.Serializable)x$11 -> BoxesRunTime.boxToBoolean(SnowflakeCallMapper.$anonfun$decode$3(x$11))).map((Function1<Seq, Expression> & Serializable & scala.Serializable)x$12 -> (Expression)x$12.head())));
        }
        return this.lift(new PartialResult<Expression>(functionCall, new WrongNumberOfArguments("DECODE", args.size(), "at least 3", WrongNumberOfArguments$.MODULE$.apply$default$4()), PartialResult$.MODULE$.apply$default$3()));
    }

    private WhenBranch makeWhenBranch(Expression expr, Expression cond, Expression out) {
        Expression expression2 = cond;
        Literal literal = Literal$.MODULE$.Null();
        Expression expression3 = expression2;
        if (!(literal != null ? !((Object)literal).equals(expression3) : expression3 != null)) {
            return new WhenBranch(new IsNull(expr), out);
        }
        return new WhenBranch(new com.databricks.labs.morpheus.intermediate.Equals(expr, expression2), out);
    }

    private Transformation<Expression> arraySort(Seq<Expression> args) {
        return this.makeArraySort((Expression)args.head(), args.lift().apply(BoxesRunTime.boxToInteger(1)), args.lift().apply(BoxesRunTime.boxToInteger(2)));
    }

    private Transformation<Expression> makeArraySort(Expression arr, Option<Expression> sortAscending, Option<Expression> nullsFirst) {
        Expression paramSortAsc = (Expression)sortAscending.getOrElse((Function0<Literal> & Serializable & scala.Serializable)() -> Literal$.MODULE$.True());
        Transformation paramNullsFirst = (Transformation)nullsFirst.map((Function1<Expression, Transformation> & Serializable & scala.Serializable)a -> this.ok(a)).getOrElse((Function0<Transformation> & Serializable & scala.Serializable)() -> {
            Expression expression2 = paramSortAsc;
            Literal literal = Literal$.MODULE$.True();
            Expression expression3 = expression2;
            if (!(literal != null ? !((Object)literal).equals(expression3) : expression3 != null)) {
                return this.ok(Literal$.MODULE$.False());
            }
            Literal literal2 = Literal$.MODULE$.False();
            Expression expression4 = expression2;
            if (!(literal2 != null ? !((Object)literal2).equals(expression4) : expression4 != null)) {
                return this.ok(Literal$.MODULE$.True());
            }
            return this.lift(new PartialResult<Expression>(expression2, new UnsupportedArguments("ARRAY_SORT", (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)paramSortAsc), Nil$.MODULE$), UnsupportedArguments$.MODULE$.apply$default$3()), PartialResult$.MODULE$.apply$default$3()));
        });
        Transformation comparator = paramNullsFirst.flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)x$13 -> this.handleComparison$1((Expression)x$13, true)).flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)leftCond -> paramNullsFirst.flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)x$14 -> this.handleComparison$1((Expression)x$14, false)).flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)rightCond -> this.handleComparison$1(paramSortAsc, true).flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)leftSort -> this.handleComparison$1(paramSortAsc, false).map((Function1<Expression, LambdaFunction> & Serializable & scala.Serializable)rightSort -> new LambdaFunction(new Case(None$.MODULE$, (Seq<WhenBranch>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new And(new IsNull(new Id("left", Id$.MODULE$.apply$default$2())), new IsNull(new Id("right", Id$.MODULE$.apply$default$2()))), $this.zeroLiteral)), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new IsNull(new Id("left", Id$.MODULE$.apply$default$2())), (Expression)leftCond)), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new IsNull(new Id("right", Id$.MODULE$.apply$default$2())), (Expression)rightCond)), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new LessThan(new Id("left", Id$.MODULE$.apply$default$2()), new Id("right", Id$.MODULE$.apply$default$2())), (Expression)leftSort)), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new WhenBranch(new GreaterThan(new Id("left", Id$.MODULE$.apply$default$2()), new Id("right", Id$.MODULE$.apply$default$2())), (Expression)rightSort)), Nil$.MODULE$))))), new Some<Expression>($this.zeroLiteral)), (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"left"), Nil$.MODULE$))), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)new UnresolvedNamedLambdaVariable((Seq<String>)new $colon$colon<Nothing$>((Nothing$)((Object)"right"), Nil$.MODULE$))), Nil$.MODULE$)))))));
        return paramNullsFirst.flatMap((Function1<Expression, Transformation> & Serializable & scala.Serializable)pnf -> {
            Tuple2<Expression, Expression> tuple2 = new Tuple2<Expression, Expression>(paramSortAsc, (Expression)pnf);
            if (tuple2 != null) {
                Expression expression2 = tuple2._1();
                Expression expression3 = tuple2._2();
                Literal literal = Literal$.MODULE$.True();
                Expression expression4 = expression2;
                if (!(literal != null ? !((Object)literal).equals(expression4) : expression4 != null)) {
                    Literal literal2 = Literal$.MODULE$.True();
                    Expression expression5 = expression3;
                    if (!(literal2 != null ? !((Object)literal2).equals(expression5) : expression5 != null)) {
                        return this.ok(new SortArray(arr, None$.MODULE$));
                    }
                }
            }
            if (tuple2 != null) {
                Expression expression6 = tuple2._1();
                Expression expression7 = tuple2._2();
                Literal literal = Literal$.MODULE$.False();
                Expression expression8 = expression6;
                if (!(literal != null ? !((Object)literal).equals(expression8) : expression8 != null)) {
                    Literal literal3 = Literal$.MODULE$.False();
                    Expression expression9 = expression7;
                    if (!(literal3 != null ? !((Object)literal3).equals(expression9) : expression9 != null)) {
                        return this.ok(new SortArray(arr, new Some<Expression>(Literal$.MODULE$.False())));
                    }
                }
            }
            return comparator.map((Function1<LambdaFunction, ArraySort> & Serializable & scala.Serializable)l -> new ArraySort(arr, new Some<Expression>((Expression)l)));
        });
    }

    private Transformation<Expression> parseLastDay(Seq<Expression> args) {
        Id id;
        String part;
        Id id2;
        String part2;
        Dot dot;
        Expression expression2;
        String part3;
        if (args.length() == 1) {
            return this.ok(new LastDay((Expression)args.head()));
        }
        Set validDateParts = (Set)Predef$.MODULE$.Set().apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"YEAR", "QUARTER", "MONTH", "WEEK"}));
        Expression expression3 = (Expression)args.apply(true);
        Option<String> option = StringLiteral$.MODULE$.unapply(expression3);
        if (!option.isEmpty() && validDateParts.apply((part3 = option.get()).toUpperCase())) {
            return this.ok(this.lastDay$1(part3.toUpperCase(), args));
        }
        if (expression3 instanceof Dot && (expression2 = (dot = (Dot)expression3).right()) instanceof Id && validDateParts.apply((part2 = (id2 = (Id)expression2).id()).toUpperCase())) {
            return this.ok(this.lastDay$1(part2.toUpperCase(), args));
        }
        if (expression3 instanceof Id && validDateParts.apply((part = (id = (Id)expression3).id()).toUpperCase())) {
            return this.ok(this.lastDay$1(part.toUpperCase(), args));
        }
        return this.lift(new PartialResult<Expression>(expression3, new UnsupportedDateTimePart(expression3, UnsupportedDateTimePart$.MODULE$.apply$default$2()), PartialResult$.MODULE$.apply$default$3()));
    }

    private Transformation<Expression> toVarchar(Expression expression2, Option<Expression> format2) {
        return this.ok(new ToVarchar(expression2, format2.map((Function1<Expression, Expression> & Serializable & scala.Serializable)format -> this.translateFormat((Expression)format))));
    }

    private Expression translateFormat(Expression format) {
        Expression expression2 = format;
        Option<String> option = StringLiteral$.MODULE$.unapply(expression2);
        if (!option.isEmpty()) {
            String fmt = option.get();
            return this.translateTemporalFormatLiteral(fmt);
        }
        return this.translateTemporalFormatExpression(expression2);
    }

    public static final /* synthetic */ boolean $anonfun$objectConstruct$1(Seq x0$1) {
        Expression expression2;
        Option<String> option;
        Seq seq = x0$1;
        Some<Seq> some = Seq$.MODULE$.unapplySeq(seq);
        return !some.isEmpty() && some.get() != null && ((SeqLike)some.get()).lengthCompare(2) == 0 && !(option = StringLiteral$.MODULE$.unapply(expression2 = (Expression)((SeqLike)some.get()).apply(0))).isEmpty();
    }

    public static final /* synthetic */ boolean $anonfun$toNumber$1(Expression x$2) {
        Expression expression2 = x$2;
        Comma comma = new Comma();
        return expression2 == null ? comma != null : !expression2.equals(comma);
    }

    public static final /* synthetic */ int $anonfun$toNumber$3(Expression x$3) {
        return 2;
    }

    public static final /* synthetic */ int $anonfun$tryToNumber$2(Expression x$5) {
        return 2;
    }

    public static final /* synthetic */ Some $anonfun$regexpExtract$1(SnowflakeCallMapper $this) {
        return new Some<Literal>($this.zeroLiteral);
    }

    public static final /* synthetic */ Expression $anonfun$regexpReplace$1() {
        return Literal$.MODULE$.apply("");
    }

    public static final /* synthetic */ com.databricks.labs.morpheus.intermediate.Equals $anonfun$regexpReplace$2(int occurrence$1, Expression variable) {
        return new com.databricks.labs.morpheus.intermediate.Equals(new Dot(variable, new Id("pos", Id$.MODULE$.apply$default$2())), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(occurrence$1 - 1)));
    }

    public static final /* synthetic */ Or $anonfun$regexpReplace$3(Expression x1$1, Expression variable) {
        return new Or(new ExpressionPrecedence(new And(new com.databricks.labs.morpheus.intermediate.Equals(x1$1, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(0))), new IsNotNull(new Dot(variable, new Dot(new Id("col", Id$.MODULE$.apply$default$2()), new Id("0", true)))))), new com.databricks.labs.morpheus.intermediate.Equals(new Dot(variable, new Id("pos", Id$.MODULE$.apply$default$2())), new Subtract(x1$1, Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(1)))));
    }

    public static final /* synthetic */ Expression $anonfun$regexpInstr$1() {
        return Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(0));
    }

    public static final /* synthetic */ boolean $anonfun$translateLiteralRegexParameters$2(char x$6) {
        return x$6 != 'i';
    }

    public static final /* synthetic */ String $anonfun$translateLiteralRegexParameters$1(String x0$1, char x1$1) {
        Tuple2<String, Character> tuple2 = new Tuple2<String, Character>(x0$1, BoxesRunTime.boxToCharacter(x1$1));
        if (tuple2 != null) {
            String agg = tuple2._1();
            char item = tuple2._2$mcC$sp();
            if (item == 'c') {
                return (String)new StringOps(Predef$.MODULE$.augmentString(agg)).filter((Function1<Object, Object> & Serializable & scala.Serializable)x$6 -> BoxesRunTime.boxToBoolean(SnowflakeCallMapper.$anonfun$translateLiteralRegexParameters$2(BoxesRunTime.unboxToChar(x$6))));
            }
            if (new StringOps(Predef$.MODULE$.augmentString("ism")).contains(BoxesRunTime.boxToCharacter(item))) {
                return new StringBuilder(0).append(agg).append(item).toString();
            }
            return agg;
        }
        throw new MatchError(tuple2);
    }

    public static final /* synthetic */ ParseToTimestamp $anonfun$inferTemporalFormat$2(int n) {
        return new ParseToTimestamp(Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(n)), ParseToTimestamp$.MODULE$.apply$default$2());
    }

    public static final /* synthetic */ boolean $anonfun$inferTemporalFormat$4(String timeStr$1, String fmt) {
        return Try$.MODULE$.apply((Function0<TemporalAccessor> & Serializable & scala.Serializable)() -> DateTimeFormatter.ofPattern(fmt).parse(timeStr$1)).isSuccess();
    }

    public static final /* synthetic */ boolean $anonfun$decode$1(Seq x$10) {
        return x$10.size() == 2;
    }

    public static final /* synthetic */ boolean $anonfun$decode$3(Seq x$11) {
        return x$11.size() == 1;
    }

    private final Transformation handleComparison$1(Expression isNullOrSmallFirst, boolean nullOrSmallAtLeft) {
        Expression expression2 = isNullOrSmallFirst;
        Literal literal = Literal$.MODULE$.True();
        Expression expression3 = expression2;
        if (!(literal != null ? !((Object)literal).equals(expression3) : expression3 != null)) {
            return this.ok(nullOrSmallAtLeft ? Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(-1)) : this.oneLiteral);
        }
        Literal literal2 = Literal$.MODULE$.False();
        Expression expression4 = expression2;
        if (!(literal2 != null ? !((Object)literal2).equals(expression4) : expression4 != null)) {
            return this.ok(nullOrSmallAtLeft ? this.oneLiteral : Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(-1)));
        }
        return this.lift(new PartialResult<Expression>(expression2, new UnsupportedArguments("ARRAY_SORT", (Seq<Expression>)new $colon$colon<Nothing$>((Nothing$)((Object)isNullOrSmallFirst), Nil$.MODULE$), UnsupportedArguments$.MODULE$.apply$default$3()), PartialResult$.MODULE$.apply$default$3()));
    }

    private final Expression lastDay$1(String datePart, Seq args$9) {
        return new DateAdd(new TimestampAdd(datePart, this.oneLiteral, new TruncDate((Expression)args$9.head(), (Expression)args$9.apply(true))), Literal$.MODULE$.apply(BoxesRunTime.boxToInteger(-1)));
    }

    public SnowflakeCallMapper() {
        SyntheticNames.$init$(this);
        this.zeroLiteral = IntLiteral$.MODULE$.apply(0);
        this.oneLiteral = IntLiteral$.MODULE$.apply(1);
        this.unsupportedAutoTimestampFormats = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new String[]{"yyyy-MM-dd'T'HH:mmXXX", "yyyy-MM-dd HH:mmXXX", "EEE, dd MMM yyyy HH:mm:ss ZZZ", "EEE, dd MMM yyyy HH:mm:ss.SSSSSSSSS ZZZ", "EEE, dd MMM yyyy hh:mm:ss a ZZZ", "EEE, dd MMM yyyy hh:mm:ss.SSSSSSSSS a ZZZ", "EEE, dd MMM yyyy HH:mm:ss", "EEE, dd MMM yyyy HH:mm:ss.SSSSSSSSS", "EEE, dd MMM yyyy hh:mm:ss a", "EEE, dd MMM yyyy hh:mm:ss.SSSSSSSSS a", "M/dd/yyyy HH:mm:ss", "EEE MMM dd HH:mm:ss ZZZ yyyy"}));
        this.unsupportedAutoTimeFormats = new $colon$colon<Nothing$>((Nothing$)((Object)"HH:MM:ss.SSSSSSSSS"), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)"HH:MM:ss"), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)"HH:MM"), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)"hh:MM:ss.SSSSSSSSS a"), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)"hh:MM:ss a"), (List<Nothing$>)new $colon$colon<Nothing$>((Nothing$)((Object)"hh:MM a"), Nil$.MODULE$))))));
        this.temporalFormatMapping = (Seq)Seq$.MODULE$.apply(Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[]{Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("YYYY"), "yyyy"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("YY"), "yy"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("MON"), "MMM"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("DD"), "dd"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("DY"), "EEE"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("HH24"), "HH"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("HH12"), "hh"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("AM"), "a"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("PM"), "a"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("MI"), "mm"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("SS"), "ss"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF9"), "SSSSSSSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF8"), "SSSSSSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF7"), "SSSSSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF6"), "SSSSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF5"), "SSSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF4"), "SSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF3"), "SSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF2"), "SS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF1"), "S"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF0"), ""), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("FF"), "SSSSSSSSS"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("TZH:TZM"), "ZZZ"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("TZHTZM"), "ZZZ"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("TZH"), "ZZZ"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("UUUU"), "yyyy"), Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(Predef$.MODULE$.ArrowAssoc("\""), "'")}));
    }
}

