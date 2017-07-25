import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { Link } from 'react-router';

export default class TermTable extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }


    render() {
        return  <table className="table table-stripped table-bordered">
                    <thead>
                    <tr>
                        <th>Termes</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.terms.map(term =>
                            <tr key={term.get('date')}>
                                <td>
                                    <Link to={"/terms/" + term.get('term')}><strong>{term.get('term')}</strong></Link>
                                </td>
                            </tr>
                        )
                    }

                    </tbody>
                </table>

    }
}
